package wat.ai.services.reports;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.stereotype.Service;
import wat.ai.config.MySQLConnection;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ReportServiceImpl implements IReportService {
    static final Logger LOGGER = Logger.getLogger(ReportServiceImpl.class.getName());

    @Override
    public void generateRaport(HttpServletResponse response) {
        try {
            InputStream jasperStream = this.getClass().getResourceAsStream("/jasper/Simple_Blue.jasper");
            Map<String, Object> params = fillMapParams();
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
            Connection connection = MySQLConnection.getMySQLConnection();

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, connection);

            response.setContentType("application/x-pdf");
            response.setHeader("Content-disposition", "inline; filename=Raport.pdf");

            final OutputStream outStream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
        }catch (Exception e){
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
    }

    public  Map<String, Object> fillMapParams(){
        Map<String, Object> paramsMap = new HashMap<>();
        return paramsMap;
    }
}
