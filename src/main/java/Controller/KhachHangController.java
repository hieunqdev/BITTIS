package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import DAO.KhachHangDAO;
import Model.KhachHang;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Servlet implementation class KhachHangController
 */
@WebServlet("/register")
public class KhachHangController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("view/register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if (action.equals("register")) {
			try {
				register(request, response);
			} catch (ServletException | IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		String MaKHRandom = generateRandomString(4);
		String Ho = request.getParameter("Ho");
		String Ten = request.getParameter("Ten");
		boolean GioiTinh = request.getParameter("Ten").equals("male");
		
		String NgaySinhStr = request.getParameter("NgaySinh");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date NgaySinh = dateFormat.parse(NgaySinhStr);
		
		String Email = request.getParameter("Email");
		String SDT = request.getParameter("SDT");
		String MatKhau = request.getParameter("MatKhau");
		
		KhachHangDAO khd = new KhachHangDAO();
		KhachHang kh = new KhachHang(MaKHRandom, Ho, Ten, GioiTinh, NgaySinh, Email, SDT, MatKhau);
		khd.create(kh);
	}
	
	public static String generateRandomString(int length) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        return IntStream.range(0, length)
                .mapToObj(i -> String.valueOf(alphabet.charAt((int) (Math.random() * alphabet.length()))))
                .collect(Collectors.joining());
    }

}
