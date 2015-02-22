/**
 * Copyright (c) 2014 MNCC
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
package fr.mncc.gwttoolbox.appengine.server.nextgen;

import fr.mncc.gwttoolbox.appengine.server.nextgen.interfaces.GaeEntity;
import fr.mncc.gwttoolbox.primitives.shared.Entity;
import fr.mncc.gwttoolbox.primitives.shared.EntityImpl;
import fr.mncc.gwttoolbox.primitives.shared.Function;
import fr.mncc.gwttoolbox.primitives.shared.Lists;

import java.util.HashMap;
import java.util.Map;

public final class Entities {

  public static EntityImpl export(Entity entity) {
    if (entity instanceof GaeEntity) {
      return ((GaeEntity) entity).convert();
    } else if (entity instanceof EntityImpl) {
      return (EntityImpl) entity;
    }
    return null;
  }

  public static Iterable<Entity> export(Iterable<Entity> entities) {
    return Lists.map(entities, new Function<Entity, Entity>() {

      @Override
      public Entity apply(Entity entity) {
        return Entities.export(entity);
      }
    });
  }

  public static Map<Long, Entity> export(Map<Long, Entity> entities) {
    Map<Long, Entity> map = new HashMap<Long, Entity>();
    for (Long id : entities.keySet()) {
      map.put(id, Entities.export(entities.get(id)));
    }
    return map;
  }
}
