package com.kh.member.controller;

import java.util.ArrayList;
import java.util.List;

import com.kh.member.model.dao.MemberDAO;
import com.kh.member.model.exception.MemberException;
import com.kh.member.model.vo.Member;
import com.kh.member.service.MemberService;
import com.kh.member.view.MemberMenu;

public class MemberController {

	public void exitProgram() {
		new MemberService().exitProgram();
	}

	// view와 dao(db연결)을 연결해주는 객체
	// view <-> controller <-> dao <-> db
	public void selectAll() {

		MemberMenu menu = new MemberMenu();
		ArrayList<Member> list;
		
		try {
			list = new MemberService().selectAll();

			if (!list.isEmpty()) {
				menu.displayMemberList(list);
			} else {
				menu.displayNoData();
			}
		} catch (MemberException e) {
//			e.printStackTrace();
			menu.displayError("회원 전체 조회 실패, 관리자에게 문의하세요");
			System.out.println(e.getMessage());
		}
	}

	public void selectOne(String memberId) {

		MemberMenu menu = new MemberMenu();
		Member m;
		
		try {
			m = new MemberService().selectOne(memberId);

			if (m != null) {
				menu.displayMember(m);
			} else {
				menu.displayNoData();
			}
		} catch (MemberException e) {
//			e.printStackTrace();
			menu.displayError("회원 조회 실패, 관리자에게 문의하세요");
			System.out.println(e.getMessage());
		}

	}

	public void selectName(String memberName) {
		
		MemberMenu menu = new MemberMenu();
		List<Member> list;
		
		try {
			list = new MemberService().selectName(memberName);
			if (!list.isEmpty()) {
				menu.displayMemberList(list);
			} else {
				menu.displayNoData();
			}
		} catch (MemberException e) {
//			e.printStackTrace();
			menu.displayError("회원 조회 실패, 관리자에게 문의하세요");
			System.out.println(e.getMessage());
		}

	}

	public void insertMember(Member m) {
		
		int result;
		
		try {
			result = new MemberService().insertMember(m);
			if (result > 0) {
				new MemberMenu().displaySuccess("회원 가입 성공");
			}
		} catch (MemberException e) {
//			e.printStackTrace();
			new MemberMenu().displayError("회원 가입 실패, 관리자에게 문의하세요");
			System.out.println(e.getMessage());
		} // 성공하면 1 반환

	}

	public void updateMember(Member m) {
		
		int result;
		
		try {
			result = new MemberService().updateMember(m);
			if (result > 0) {
				new MemberMenu().displaySuccess("회원 수정 성공");
			}
		} catch (MemberException e) {
//			e.printStackTrace();
			new MemberMenu().displayError("회원 수정 실패, 관리자에게 문의하세요");
			System.out.println(e.getMessage());
		}

	}

	public void deleteMember(String memberId) {
		
		int result;
		
		try {
			result = new MemberService().deleteMember(memberId);
			if (result > 0) {
				new MemberMenu().displaySuccess("회원 탈퇴 성공");
			}
		} catch (MemberException e) {
//			e.printStackTrace();
			new MemberMenu().displayError("회원 탈퇴 실패, 관리자에게 문의하세요");
			System.out.println(e.getMessage());
		} // 성공하면 1 반환

	}

	public void selectAllDeleteMember() {
		
		MemberMenu menu = new MemberMenu();
		List<Member> list;
		
		try {
			list = new MemberService().selectAllDeleteMember();
			if (!list.isEmpty()) {
				menu.displayMemberList(list);
			} else {
				menu.displayNoData();
			}
		} catch (MemberException e) {
//			e.printStackTrace();
			menu.displayError("탈퇴 회원 조회 실패, 관리자에게 문의하세요");
			System.out.println(e.getMessage());
		}

	}

}
