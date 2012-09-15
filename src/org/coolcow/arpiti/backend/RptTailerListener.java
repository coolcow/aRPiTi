package org.coolcow.arpiti.backend;

import java.util.List;
import org.coolcow.arpiti.backend.rptline.AbstractRptLine;

public interface RptTailerListener {

    public void rptLineTailed(AbstractRptLine rptLine);

    public void rptLinesTailed(List<AbstractRptLine> rptLines);

    public void tailingResumed();

    public void tailingWait();
}