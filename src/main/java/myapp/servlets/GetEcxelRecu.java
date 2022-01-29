package myapp.servlets;


import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import myapp.models.abonnement;
import myapp.models.facture;
import myapp.models.user;

/**
 * Servlet implementation class GetEcxelRecu
 */
@WebServlet("/GetEcxelRecu")
public class GetEcxelRecu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetEcxelRecu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		user u=(user) request.getSession().getAttribute("user");
		facture f=(facture) request.getSession().getAttribute("facture");
		abonnement b=(abonnement) request.getSession().getAttribute("abonnement");
		String s=(String) request.getSession().getAttribute("num_carte");
		@SuppressWarnings("unchecked")
		Hashtable<String, String> r= (Hashtable<String, String>) request.getSession().getAttribute("r");
		
		try (HSSFWorkbook workbook = new HSSFWorkbook()) {
			CellStyle headerCellStyle = workbook.createCellStyle();
            HSSFFont font = workbook.createFont();
            font.setBold( true );
            headerCellStyle.setFont(font);  
            headerCellStyle.setAlignment( HorizontalAlignment.CENTER ); 
            
			HSSFSheet sheet = workbook.createSheet("Recu");  
			
			HSSFRow row = sheet.createRow((short)0);  
			
			HSSFCell cell;
			
			cell=row.createCell(0);
            cell.setCellStyle( headerCellStyle);
            cell.setCellValue(r.get("title_invoice_user"));  

			 
			row = sheet.createRow((short)2);  
			row.createCell(0).setCellValue(r.get("last_name"));  
			row.createCell(1).setCellValue(u.getNom());  
			  
			row = sheet.createRow((short)3);  
			row.createCell(0).setCellValue(r.get("first_name"));  
			row.createCell(1).setCellValue(u.getPrenom());  
			  
			row = sheet.createRow((short)4);  
			row.createCell(0).setCellValue(r.get("user_id"));  
			row.createCell(1).setCellValue(u.getId()); 
			
			row = sheet.createRow((short)5);  
			row.createCell(0).setCellValue(r.get("email_address"));  
			row.createCell(1).setCellValue(u.getEmail()); 
			
			row = sheet.createRow((short)7);  
			row.createCell(0).setCellValue(r.get("abn_name"));  
			row.createCell(1).setCellValue(b.getNom()); 
			 
			row = sheet.createRow((short)8);  
			row.createCell(0).setCellValue("ID Abonnement");  
			row.createCell(1).setCellValue(b.getId()); 
			 
			row = sheet.createRow((short)9);  
			row.createCell(0).setCellValue(r.get("price"));  
			row.createCell(1).setCellValue(b.getPrice()+" DH"); 
			 
			row = sheet.createRow((short)10);  
			row.createCell(0).setCellValue(r.get("begin_date"));  
			row.createCell(1).setCellValue(f.getDate_debut().toString()); 
			 
			row = sheet.createRow((short)12);  
			row.createCell(0).setCellValue(r.get("amount"));  
			row.createCell(1).setCellValue(b.getPrice()+"DH"); 
			 
			 
			 
			row = sheet.createRow((short)13);  
			row.createCell(0).setCellValue(r.get("card_number"));  
			row.createCell(1).setCellValue(s); 
			  
			
			sheet.autoSizeColumn( 0 );
			sheet.autoSizeColumn( 1 );

			 
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", "MonRecu.xls");
			response.setContentType( "application/vnd.ms-excel" );
			response.setHeader(headerKey, headerValue);
			
			
			 try ( OutputStream out = response.getOutputStream() ) {
			     workbook.write( out );
			 }
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
