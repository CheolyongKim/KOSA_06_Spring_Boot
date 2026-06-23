package kr.or.kosa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
  create table kosauser(
     id number primary key,
     username varchar2(50),
     password varchar2(50),
     email varchar2(50)
  ); 
  
  create SEQUENCE user_id
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
   private long id;
   private String username;
   private String password;
   private String email;
}
