package com.newlecture.web.controller.admin.board;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/admin/board/notice/")
@Controller("adminNoticeController")
public class NoticeController {
	
	@Autowired
	private ServletContext ctx; 
	
	@RequestMapping("list")
	public String list() {
		
		return "admin.board.notice.list";
	}
	
	@GetMapping("reg")
	public String reg(){
		
		return "admin.board.notice.reg";
	}
	
	@PostMapping("reg")
	public String reg(String title, 
			String content, 
			MultipartFile[] files, 
			String category, 
			String[] sexs, 
			String food) throws IllegalStateException, IOException {

		for(MultipartFile file : files) {
			
			long fileSize = file.getSize();
			String fileName = file.getOriginalFilename();
			System.out.printf("fileName:%s , fileSize:%d\n", fileName, fileSize);
			
			String webPath ="/static/upload";
			String realPath = ctx.getRealPath(webPath) ;
			System.out.printf("before realPath : %s\n", realPath);
			
			File savePath = new File(realPath);
			if(!savePath.exists()) {
				System.out.printf("there is no folder i will make it");
				savePath.mkdirs();
			}
			
			realPath += File.separator + fileName;
	
			System.out.printf("after realPath : %s\n", realPath);
			
			File saveFile = new File(realPath);
			
			file.transferTo(saveFile);
		}
		
		/* sex : 체크박스예제 */
		for(String sex : sexs)
			System.out.println(sex);
		
		/* category : 콤보박스예제 food : 라디오버튼예제 */
		//return String.format("title:%s, content:%s , category:%s , food:%s<br>", title, content, category, food);
		return "admin.board.notice.reg";
	}
	 
	@RequestMapping("edit")
	public String edit() {
		
		return "admin.board.notice.edit";
	}
	
	@RequestMapping("del")
	public String del() {
		
		return "";
	}
	
}
