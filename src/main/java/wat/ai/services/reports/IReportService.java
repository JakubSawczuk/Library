package wat.ai.services.reports;

import javax.servlet.http.HttpServletResponse;

public interface IReportService {

    void generateReport(HttpServletResponse response);
}
