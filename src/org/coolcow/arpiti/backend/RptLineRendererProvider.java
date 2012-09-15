/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.backend;

import java.util.HashMap;
import java.util.Map;
import org.coolcow.arpiti.backend.rptline.AbstractRptLine;
import org.coolcow.arpiti.gui.rptline.AbstractRptLineRenderer;
import org.coolcow.arpiti.gui.rptline.DefaultRenderer;
import org.coolcow.arpiti.gui.rptline.DisconnectStartIRenderer;
import org.coolcow.arpiti.gui.rptline.DwDebugFpsRenderer;
import org.coolcow.arpiti.gui.rptline.LocalityEventRenderer;
import org.coolcow.arpiti.gui.rptline.LoginAttemptRenderer;
import org.coolcow.arpiti.gui.rptline.LoginLoadedRenderer;
import org.coolcow.arpiti.gui.rptline.LoginPublishingRenderer;
import org.coolcow.arpiti.gui.rptline.ObjRenderer;
import org.coolcow.arpiti.gui.rptline.StartingLoginRenderer;

/**
 *
 * @author jruiz
 */
public abstract class RptLineRendererProvider {

    final static Map<AbstractRptLine.Type, AbstractRptLineRenderer> rendererMap = new HashMap<>();

    private RptLineRendererProvider() {
    }

    public static AbstractRptLineRenderer getRenderer(final AbstractRptLine.Type type) {
        if (!rendererMap.containsKey(type)) {
            final AbstractRptLineRenderer renderer;
            if (type == null) {
                renderer = new DefaultRenderer();
            } else {
                switch (type) {
                    case STARTING_LOGIN: { renderer = new StartingLoginRenderer(); } break;
                    case LOGIN_ATTEMPT: { renderer = new LoginAttemptRenderer(); } break;
                    case LOGIN_PUBLISHING: { renderer = new LoginPublishingRenderer(); } break;
                    case LOGIN_LOADED: { renderer = new LoginLoadedRenderer(); } break;
                    case DW_DEBUG_FPS: { renderer = new DwDebugFpsRenderer(); } break;
                    case LOCALITY_EVENT: { renderer = new LocalityEventRenderer(); } break;
                    case DISCONNECT_START_I: { renderer = new DisconnectStartIRenderer(); } break;
                    case OBJ: { renderer = new ObjRenderer(); } break;
                    default: renderer = new DefaultRenderer();
                }
            }
            rendererMap.put(type, renderer);
        }
        return rendererMap.get(type);
    }
}
