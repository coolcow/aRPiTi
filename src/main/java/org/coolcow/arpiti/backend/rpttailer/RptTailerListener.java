package org.coolcow.arpiti.backend;

import java.util.List;
import org.coolcow.arpiti.backend.rptline.AbstractRptLine;

public interface RptTailerListener {

    public void tailingStarted(final long byteLength);

    public void tailingProceed(final long byteRead);
    
    public void rptLinesTailed(List<AbstractRptLine> rptLines);

    public void tailingResumed();   

    public void tailingWait();
    
    public void tailingStopped();
    
}