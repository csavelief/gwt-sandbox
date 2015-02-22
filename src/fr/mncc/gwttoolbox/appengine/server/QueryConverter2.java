/**
 * Copyright (c) 2013 MNCC
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 * @author http://www.mncc.fr
 */
package fr.mncc.gwttoolbox.appengine.server;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PropertyProjection;
import com.google.appengine.api.datastore.Query;
import fr.mncc.gwttoolbox.appengine.shared.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QueryConverter2 {

  public static Query getAsAppEngineQuery(SQuery2 squery) {
    Query query =
        hasAncestor(squery) ? new Query(squery.getKind(), KeyFactory.createKey(squery
            .getAncestorKind(), squery.getAncestorId())) : new Query(squery.getKind());

    // Set keys only
    if (squery.isKeysOnly())
      query.setKeysOnly();

    // Add projections
    for (Projection2 projection : squery.getProjections())
      query.addProjection(new PropertyProjection(projection.getPropertyName(), projection
          .getClazz()));

    // Add sort order
    for (Sort2 sorter : squery.getSorters())
      query.addSort(sorter.getPropertyName(), sorter.isAscending() ? Query.SortDirection.ASCENDING
          : Query.SortDirection.DESCENDING);

    // Add clause
    if (squery.getClause() != null)
      query.setFilter(buildClause(squery, squery.getClause()));
    return query;
  }

  private static Query.Filter buildClause(SQuery2 squery, Clause2 clause) {
    if (clause.isLeaf()) {
      Filter2 sfilter = (Filter2) clause;
      if (sfilter.getOperator() != FilterOperator2.IN) {
        if (sfilter.getPropertyName().equals("__key__")
            && sfilter.getPropertyValue() instanceof Long) {
          if (hasAncestor(squery))
            return new Query.FilterPredicate(sfilter.getPropertyName(), getAsFilterOperator(sfilter
                .getOperator()), KeyFactory.createKey(KeyFactory.createKey(
                squery.getAncestorKind(), squery.getAncestorId()), squery.getKind(), (Long) sfilter
                .getPropertyValue()));
          return new Query.FilterPredicate(sfilter.getPropertyName(), getAsFilterOperator(sfilter
              .getOperator()), KeyFactory.createKey(squery.getKind(), (Long) sfilter
              .getPropertyValue()));
        }
        if (sfilter.getPropertyValue() instanceof Timestamp)
          return new Query.FilterPredicate(sfilter.getPropertyName(), getAsFilterOperator(sfilter
              .getOperator()), new Date(((Timestamp) sfilter.getPropertyValue()).getTime()));
        if (sfilter.getPropertyValue() instanceof Time)
          return new Query.FilterPredicate(sfilter.getPropertyName(), getAsFilterOperator(sfilter
              .getOperator()), new Date(((Time) sfilter.getPropertyValue()).getTime()));
        return new Query.FilterPredicate(sfilter.getPropertyName(), getAsFilterOperator(sfilter
            .getOperator()), sfilter.getPropertyValue());
      }

      List<Key> keys = new ArrayList<Key>();
      for (Long id : sfilter.getPropertyValues()) {
        if (hasAncestor(squery))
          keys.add(KeyFactory.createKey(KeyFactory.createKey(squery.getAncestorKind(), squery
              .getAncestorId()), squery.getKind(), id));
        else
          keys.add(KeyFactory.createKey(squery.getKind(), id));
      }
      return new Query.FilterPredicate("__key__", Query.FilterOperator.IN, keys);
    }
    if (clause.isAnd())
      return Query.CompositeFilterOperator.and(buildClause(squery, clause.getLeftClause()),
          buildClause(squery, clause.getRightClause()));
    return Query.CompositeFilterOperator.or(buildClause(squery, clause.getLeftClause()),
        buildClause(squery, clause.getRightClause()));
  }

  private static Query.FilterOperator getAsFilterOperator(int operator) {
    if (operator == FilterOperator2.EQUAL)
      return Query.FilterOperator.EQUAL;
    else if (operator == FilterOperator2.LESS_THAN)
      return Query.FilterOperator.LESS_THAN;
    else if (operator == FilterOperator2.LESS_THAN_OR_EQUAL)
      return Query.FilterOperator.LESS_THAN_OR_EQUAL;
    else if (operator == FilterOperator2.GREATER_THAN)
      return Query.FilterOperator.GREATER_THAN;
    else if (operator == FilterOperator2.GREATER_THAN_OR_EQUAL)
      return Query.FilterOperator.GREATER_THAN_OR_EQUAL;
    else if (operator == FilterOperator2.NOT_EQUAL)
      return Query.FilterOperator.NOT_EQUAL;
    else if (operator == FilterOperator2.IN)
      return Query.FilterOperator.IN;
    return null; // This case should never happen
  }

  private static boolean hasAncestor(SQuery2 squery) {
    return squery.getAncestorKind() != null && !squery.getAncestorKind().isEmpty()
        && squery.getAncestorId() > 0;
  }
}
