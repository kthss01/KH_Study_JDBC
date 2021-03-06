package com.kh.member.controller;

import java.util.ArrayList;
import java.util.List;

import com.kh.member.model.dao.MemberDAO;
import com.kh.member.model.vo.Member;
import com.kh.member.view.MemberMenu;

public class MemberController {

	// view와 dao(db연결)을 연결해주는 객체
	// view <-> controller <-> dao <-> db
	public void selectAll() {
		
		MemberMenu menu = new MemberMenu();
		ArrayList<Member> list = new MemberDAO().selectAll();
		
		if (!list.isEmpty()) {
			menu.displayMemberList(list);
		} else {
			menu.displayError("해당되는 데이터가 없습니다.");
		}
	}

	public void selectOne(String memberId) {
		
		MemberMenu menu = new MemberMenu();
		Member m = new MemberDAO().selectOne(memberId);
		
		if (m != null) {
			menu.displayMember(m);
		} else {
			menu.displayError(memberId + "에 해당되는 데이터가 없습니다.");
		}
	}
	
	public void selectOneByName(String memberName) {
		MemberMenu menu = new MemberMenu();
		Member m = new MemberDAO().selectOneByName(memberName);
		
		if (m != null) {
			menu.displayMember(m);
		} else {
			menu.displayError(memberName + "에 해당되는 데이터가 없습니다.");
		}
	}

	public void insertMember(Member m) {
		int result = new MemberDAO().insertMember(m); // 성공하면 1 반환
		
		if (result > 0) {
			new MemberMenu().displaySuccess("회원가입 성공");
		} else {
			new MemberMenu().displayError("회원가입 실패");
		}
	}

	public void deleteMember(String memberId) {
		int result = new MemberDAO().deleteMember(memberId); // 성공하면 1 반환
		
		if (result > 0) {
			new MemberMenu().displaySuccess("회원 탈퇴 성공");
		} else {
			new MemberMenu().displayError("회원 탈퇴 실패");
		}
	}

	public void updateMember(String memberId) {
		MemberMenu menu = new MemberMenu();
		Member m = new MemberDAO().selectOne(memberId);
		
		if (m != null) {
			menu.displayMember(m);
		} else {
			menu.displayError(memberId + "에 해당되는 데이터가 없습니다.");
			return;
		}
		
		m = menu.updateMember(m);
		
		int result = new MemberDAO().updateMember(memberId, m); // 성공하면 1 반환
		
		if (result > 0) {
			new MemberMenu().displaySuccess("회원 정보변경 성공");
		} else {
			new MemberMenu().displayError("회원 정보변경 실패");
		}
	}

	public void selectName(String memberName) {
		MemberMenu menu = new MemberMenu();
		List<Member> list = new MemberDAO().selectName(memberName);
		
		if (!list.isEmpty()) {
			menu.displayMemberList(list);
		} else {
			menu.displayError("해당되는 데이터가 없습니다.");
		}
	}

	public void updateMember(Member m) {
		int result = new MemberDAO().updateMember(m);
		
		if (result > 0) {
			new MemberMenu().displaySuccess("회원 수정 성공");
		} else {
			new MemberMenu().displayError("회원 수정 실패");
		}
	}


}
