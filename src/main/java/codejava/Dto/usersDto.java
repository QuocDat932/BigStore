package codejava.Dto;

import codejava.Entity.roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class usersDto {
	private static final long serialVersionUID = 1L;
	private int id;//
	private String fullname;
	private String email;
	private String imgUrl;
	private Boolean isDeleted;
	private String type_account;
	private String username;
	private String hashPassword;
	private String phone;
	private String address;
	private int roleId;
	private String content;
	private roles role;
	private String description;
}
