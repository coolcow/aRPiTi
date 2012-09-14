/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.backend;

import java.util.HashMap;
import java.util.Map;
import org.coolcow.arpiti.gui.entityrenderer.AbstractEntityRenderer;
import org.coolcow.arpiti.gui.entityrenderer.DefaultEntityRenderer;
import org.coolcow.arpiti.gui.entityrenderer.DisconnectStartIRenderer;
import org.coolcow.arpiti.gui.entityrenderer.DwDebugFpsRenderer;
import org.coolcow.arpiti.gui.entityrenderer.LocalityEventRenderer;
import org.coolcow.arpiti.gui.entityrenderer.LoginAttemptRenderer;
import org.coolcow.arpiti.gui.entityrenderer.LoginLoadedRenderer;
import org.coolcow.arpiti.gui.entityrenderer.LoginPublishingRenderer;
import org.coolcow.arpiti.gui.entityrenderer.StartingLoginRenderer;

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
                    case STARTING_LOGIN: { 
                        renderer = new StartingLoginRenderer();
                    } break;
                    case LOGIN_ATTEMPT: { 
                        renderer = new LoginAttemptRenderer();
                    } break;
                    case LOGIN_PUBLISHING: { 
                        renderer = new LoginPublishingRenderer();
                    } break;
                    case LOGIN_LOADED: { 
                        renderer = new LoginLoadedRenderer();
                    } break;
                    case DW_DEBUG_FPS: { 
                        renderer = new DwDebugFpsRenderer();
                    } break;
                    case LOCALITY_EVENT: { 
                        renderer = new LocalityEventRenderer();
                    } break;
                    case DISCONNECT_START_I: { 
                        renderer = new DisconnectStartIRenderer();
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
