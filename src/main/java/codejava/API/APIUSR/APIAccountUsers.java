package codejava.API.APIUSR;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import codejava.Constant.MessageAPI;
import codejava.Constant.SessionConst;
import codejava.Dto.Message;
import codejava.Dto.usersDto;
import codejava.Entity.Account;
import codejava.Entity.AccountGG;
import codejava.Entity.Users;
import codejava.Services.AccountGGService;
import codejava.Services.AccountService;
import codejava.Services.UserServices;

@RestController
@RequestMapping("/api/account")
public class APIAccountUsers {
	@Autowired
	private UserServices userservices;
	@Autowired
	private AccountService accountServices;
	@Autowired
	private AccountGGService accGGServ;
	private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
	private static final Pattern WHITESPACE = Pattern.compile("[\\s]");
	
	@PostMapping("/users/update")
	public ResponseEntity<?> insertUser(@RequestBody usersDto newUser) throws IOException {
		Message msg = new Message();
//		// tạo slug ảnh
		try {
		String input = newUser.getUsername();
		String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
		String normalized = Normalizer.normalize(nowhitespace, Form.NFD);
		String slug = NONLATIN.matcher(normalized).replaceAll("");
		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		
		System.out.println("hinh:" + newUser.getImgUrl());
		// tạo base64
		String extension = "jpg";
		if (newUser.getImgUrl() != null) {
			String base64String = newUser.getImgUrl();
			String[] strings = base64String.split(",");

			// convert base64 string to binary data
			Path path1 = Paths.get("src/main/resources/static/admin/images/faces/");
			System.out.println("string:" + strings.length);
			if (strings.length > 1) {

				switch (strings[0]) {// check image's extension
				case "data:image/jpeg;base64":
					extension = "jpg";
					break;
				case "data:image/png;base64":
					extension = "png";
					break;
				case "data:image/jpg;base64":
					extension = "jpg";
					break;
				default:// should write cases for more images types
					extension = "false";
					break;
				}

				// đổi và add hình vào

				if (extension == "false") {
					msg.setStatus("IMG must type PNG, JPG");
					return ResponseEntity.ok(msg);
				}
				byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
				String path = path1 + "//" + slug.toLowerCase(Locale.ENGLISH) + "." + extension;
				File file = new File(path);

				try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
					outputStream.write(data);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		Users users = userservices.findByid(newUser.getId());
		userservices.findByid(newUser.getId());
		users.setFullname(newUser.getFullname());
		users.setPhone(newUser.getPhone());
		users.setAddress(newUser.getAddress());
		System.out.println("emai" + newUser.getEmail());
		
		
//		System.out.println(""+checkuseremail.getEmail());
		if (userservices.findByEmail(newUser.getEmail()) != null) {
			if (!newUser.getEmail().equalsIgnoreCase(userservices.findByEmail(newUser.getEmail()).getEmail()) ) {
				msg.setStatus("Has Email");
				System.out.println("email da co ");
				return ResponseEntity.ok(MessageAPI.message(MessageAPI.FAIL, "Something Wrong ", null));
			}
		}
		
		if (!newUser.getEmail().matches(EMAIL_PATTERN)) {
			msg.setStatus("Wrong Format Email");
			return ResponseEntity.ok(MessageAPI.message(MessageAPI.FAIL, "Something Wrong ", null));

		} else {
			users.setEmail(newUser.getEmail());
		}	
		if (newUser.getImgUrl() != null) {
			users.setImgUrl(slug.toLowerCase(Locale.ENGLISH) + "." + extension);
		} else {
			users.setImgUrl("null" + "." + extension);
		}
		userservices.SaveAndUpdate(users);
			msg.setStatus("Successfully !!!");
			System.out.println("success'");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setStatus("Failure !!!");
			System.out.println("fail'");
		}
		return ResponseEntity.ok(MessageAPI.message(MessageAPI.FAIL, "Something Wrong ", null));
	}
	
	
	@GetMapping("/users/loadAccount")
	public ResponseEntity<?> insertUser(HttpSession sess)  {
		Users u = (Users) sess.getAttribute(SessionConst.CURRENT_USER);
		Users users = userservices.findByid(u.getId());
		Account account = accountServices.findByUsers_Id(u.getId());
		if (Objects.isNull(account)) {
			AccountGG AccountGGIndex = accGGServ.findByUsers_Id(users.getId());
			return ResponseEntity.ok(AccountGGIndex);
		} else {
			return ResponseEntity.ok(account);
		}
		
	};
}
