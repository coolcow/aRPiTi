package org.coolcow.arpiti.backend;

import java.util.List;
import org.coolcow.arpiti.rptline.RptLine;

public interface RptTailerListener {

    public void rptLineTailed(RptLine rptLine);
    public void rptLinesTailed(List<RptLine> rptLines);
    public void tailingResumed();
    public void tailingWait();
    
}