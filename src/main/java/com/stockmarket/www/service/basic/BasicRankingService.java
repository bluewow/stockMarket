//package com.stockmarket.www.service.basic;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.stockmarket.www.dao.HaveStockDao;
//import com.stockmarket.www.dao.MemberDao;
//import com.stockmarket.www.entity.HaveStockView;
//import com.stockmarket.www.entity.Member;
//import com.stockmarket.www.entity.MemberView;
//import com.stockmarket.www.service.RankingService;
//
//@Service
//public class BasicRankingService implements RankingService {
//	
//	@Autowired
//	private MemberDao memberDao;
//	@Autowired
//	private HaveStockDao haveStockDao;
//
//	@Override
//	public List<MemberView> getMemberList() {
//		List<Member> tempMember = memberDao.getRankerList();
//		List<MemberView> members = new ArrayList<>();
//		
//		// 현재 보유 자산
//		for(Member m:tempMember) {
//			long sum = m.getvMoney();
//			
//			List<HaveStockView> list = haveStockDao.getList(m.getId());
//			
//			if(list == null)
//				continue;
//			
//			for (HaveStockView data : list) {
//				long presentValue = Long.parseLong(data.getPrice().replaceAll(",", ""));
//				long quantity = data.getQuantity();
//				sum += presentValue * quantity;
//			}
//			MemberView memberView = new MemberView(
//					m.getId(), 
//					m.getEmail(), 
//					m.getNickName(), 
//					m.getPassword(), 
//					m.getvMoney(), 
//					m.getCardPos(), 
//					m.getProfileImg()
//			);
//			memberView.setTotalAsset(sum);
//			members.add(memberView);
//		}
//		return members;
//	}
//
//	@Override
//	public MemberView getMember(int id) {
//		Member tempMember = memberDao.getMember(id);
//		MemberView member = new MemberView(
//				tempMember.getId(), 
//				tempMember.getEmail(), 
//				tempMember.getNickName(), 
//				tempMember.getPassword(), 
//				tempMember.getvMoney(), 
//				tempMember.getCardPos(), 
//				tempMember.getProfileImg()
//		);
//		// 현재 보유 자산
//		long sum = member.getvMoney();
//
//		List<HaveStockView> list = haveStockDao.getList(id);
//		
//		for (HaveStockView data : list) {
//			long presentValue = Long.parseLong(data.getPrice().replaceAll(",", ""));
//			long quantity = data.getQuantity();
//			sum += presentValue * quantity;
//		}
//		member.setTotalAsset(sum);
//		return member;
//	}
//
//	@Override
//	public int getMemberRank(int id) {
//		return memberDao.getMemberRank(id);
//	}
//}
