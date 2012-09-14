/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.backend.entities;

import java.util.HashMap;
import java.util.Map;
import org.coolcow.arpiti.backend.RptLine;
import org.coolcow.arpiti.gui.entityrenderer.AbstractEntityRenderer;
import org.coolcow.arpiti.gui.entityrenderer.DefaultEntityRenderer;
import org.coolcow.arpiti.gui.entityrenderer.DwDebugFpsRenderer;
import org.coolcow.arpiti.gui.entityrenderer.LocalityEventRenderer;
import org.coolcow.arpiti.gui.entityrenderer.LoginAttemptRenderer;

/**
 *
 * @author jruiz
 */
public abstract class EntityRendererProvider {
    
    final static Map<RptLine.Type, AbstractEntityRenderer> rendererMap = new HashMap<>();

    private EntityRendererProvider() {        
    }
    
    public static AbstractEntityRenderer getEntityRenderer(final RptLine.Type type) {
        if (!rendererMap.containsKey(type)) {
            final AbstractEntityRenderer renderer;
            if (type == null) {
                renderer = new DefaultEntityRenderer();
            } else {
                switch (type) {
                    case LOGIN_ATTEMPT: { 
                        renderer = new LoginAttemptRenderer();
                    } break;
                    case DW_DEBUG_FPS: { 
                        renderer = new DwDebugFpsRenderer();
                    } break;
                    case LOCALITY_EVENT: { 
                        renderer = new LocalityEventRenderer();
                    } break;
                    default: 
                        renderer = new DefaultEntityRenderer();
                }
            }
            rendererMap.put(type, renderer);
        }
        return rendererMap.get(type);
    }
    
}
