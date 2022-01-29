package myapp.serviceBancaire;

import java.time.LocalDate;

import myapp.dao.BanqueDAO;
import myapp.models.banque;

public class BanqueService {
	
	
	
	public static BanqueDAO bdao=new BanqueDAO();
	
	
	
	
	public static boolean isLastNumbersValid(String num_card,String last_num_card) {
		if(bdao.findCompte(num_card) == null) {
			return false;

		}
		return bdao.findCompte(num_card).getLast_num().trim().equals(last_num_card.trim())  && last_num_card.trim().length()==3;

	}
	
	
	public static boolean isSoldeValid(String num_card,double s) {
		banque b=bdao.findCompte(num_card);
		if(b!=null && b.getSolde() >=s) {
			return true;
		}
		return false;
	}
	
	public static boolean verifyInformations(String num_carte,LocalDate date_end,String last_num_carte) {
		if(date_end.isAfter(LocalDate.now()) &&
				num_carte.length()==16 &&
				last_num_carte.length()==3 &&
				isLastNumbersValid(num_carte, last_num_carte)
				) {
			return true;
			
		}
		return false;
	}
	
	
	
	public static boolean pay(String num_carte,LocalDate date_end,String last_num_carte,double am) {
		if( verifyInformations(num_carte, date_end, last_num_carte) &&
				isSoldeValid(num_carte,am)
				) {
			
			bdao.SoldeSous(num_carte, am);
			return true;
			
		}
		return false;
	}

}
