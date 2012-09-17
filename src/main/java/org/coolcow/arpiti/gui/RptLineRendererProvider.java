/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.gui;

import java.util.HashMap;
import java.util.Map;
import org.coolcow.arpiti.backend.rptline.AbstractRptLine;
import org.coolcow.arpiti.gui.renderer.AbstractRptLineRenderer;
import org.coolcow.arpiti.gui.renderer.CleanupRenderer;
import org.coolcow.arpiti.gui.renderer.DefaultRenderer;
import org.coolcow.arpiti.gui.renderer.DisconnectStartIRenderer;
import org.coolcow.arpiti.gui.renderer.DwDebugFpsRenderer;
import org.coolcow.arpiti.gui.renderer.ErrorRenderer;
import org.coolcow.arpiti.gui.renderer.HiveRenderer;
import org.coolcow.arpiti.gui.renderer.LocalityEventRenderer;
import org.coolcow.arpiti.gui.renderer.LoginAttemptRenderer;
import org.coolcow.arpiti.gui.renderer.LoginLoadedRenderer;
import org.coolcow.arpiti.gui.renderer.LoginPublishingRenderer;
import org.coolcow.arpiti.gui.renderer.ObjRenderer;
import org.coolcow.arpiti.gui.renderer.PDeathRenderer;
import org.coolcow.arpiti.gui.renderer.ReadWriteRenderer;
import org.coolcow.arpiti.gui.renderer.SecondHandZombieInitializedRenderer;
import org.coolcow.arpiti.gui.renderer.StartingLoginRenderer;
import org.coolcow.arpiti.gui.renderer.WriteRenderer;

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
                    case ERROR: { renderer = new ErrorRenderer(); } break;
                    case SECOND_HAND_ZOMBIE_INITIALIZED: { renderer = new SecondHandZombieInitializedRenderer(); } break;
                    case PDEATH: { renderer = new PDeathRenderer(); } break;
                    case WRITE: { renderer = new WriteRenderer(); } break;
                    case CLEANUP: { renderer = new CleanupRenderer(); } break;
                    case READ_WRITE: { renderer = new ReadWriteRenderer(); } break;
                    case HIVE: { renderer = new HiveRenderer(); } break;
                    default: renderer = new DefaultRenderer();
                }
            }
            rendererMap.put(type, renderer);
        }
        return rendererMap.get(type);
    }
}
