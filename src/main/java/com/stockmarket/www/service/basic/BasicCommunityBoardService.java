package com.stockmarket.www.service.basic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockmarket.www.dao.CommunityBoardDao;
import com.stockmarket.www.entity.CommunityBoard;
import com.stockmarket.www.service.CommunityBoardService;

@Service
public class BasicCommunityBoardService implements CommunityBoardService {

	@Autowired
	private CommunityBoardDao communityBoardDao;

	// 게시판 목록 보기
	@Override
	public List<CommunityBoard> getCommunityBoardList(int page, String field, String query, String stockCode,
			int loginId) {
		return communityBoardDao.getCommunityBoardList(page, field, query, stockCode, loginId);
	}

	// 게시글과 댓글 보기
	@Override
	public CommunityBoard getBoard(int id) {
		return communityBoardDao.getCommunityBoardDetail(id);
	}

	// 게시글 쓰기
	@Override
	public int insertCommunityBoard(CommunityBoard communityBoard) {
		return communityBoardDao.insertCommunityBoard(communityBoard);
	}

	// 게시글 수정하기
	@Override
	public int updateCommunityBoard(CommunityBoard communityBoard) {
		return communityBoardDao.updateCommunityBoard(communityBoard);
	}

	// 게시글 삭제하기
	@Override
	public int deleteCommunityBoard(int boardId) {
		return communityBoardDao.deleteCommunityBoard(boardId);
	}

	// 게시글 댓글개수 보기
	@Override
	public int getCommunityBoardreplyCnt(String field, String query, String stockName) {
		return communityBoardDao.getReplyCnt(field, query, stockName);
	}

	// 게시글 댓글보기
	@Override
	public List<CommunityBoard> getCommunityBoardReplyList(int boardId) {
		return communityBoardDao.getReplyList(boardId);
	}

	// 게시글 댓글 쓰기
	@Override
	public int insertReply(CommunityBoard insertReply) {
		return communityBoardDao.insertReply(insertReply);
	}

	// 게시글의 마지막 댓글 번호 알기
	@Override
	public int lastReplyNum() {
		return communityBoardDao.lastReplyNum();
	}

	// 게시글 댓글 수정하기
	@Override
	public int updateReply(CommunityBoard updateReply) {
		return communityBoardDao.updateReply(updateReply);
	}

	// 게시글 댓글 삭제하기
	@Override
	public int deleteReply(int replyId) {
		return communityBoardDao.deleteReply(replyId);
	}

	@Override
	public int deleteReplys(int boardId) {
		return communityBoardDao.deleteReplys(boardId);
	}

	@Override
	public int selectInterestBoard(CommunityBoard selectInterestBoard) {
		return communityBoardDao.selectInterestBoard(selectInterestBoard);
	}

	@Override
	public int insertInterestBoard(CommunityBoard insertInterestBoard) {
		return communityBoardDao.insertInterestBoard(insertInterestBoard);
	}

	@Override
	public int deleteInterestBoard(CommunityBoard deleteInterestBoard) {
		return communityBoardDao.deleteInterestBoard(deleteInterestBoard);
	}
	@Override
	public int deleteInterestBoards(int boardId) {
		return communityBoardDao.deleteInterestBoards(boardId);
	}

}
