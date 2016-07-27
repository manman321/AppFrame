package com.wlht.oa.pinyin;



import com.wlht.oa.bean.Contact;

import java.util.Comparator;

/**
 * 
 * @author xiaanming
 *
 */
public class PinyinComparator2 implements Comparator<Contact> {

	private boolean ignoreDept = false;

	public PinyinComparator2(boolean ignoreDept)
	{
		this.ignoreDept = ignoreDept;
	}

	public int compare(Contact o1, Contact o2) {

		if (!ignoreDept)
		{
			if (o1.getSortDept().equals("@")
					|| o2.getSortDept().equals("#")) {
				return -1;
			} else if (o1.getSortDept().equals("#")
					|| o2.getSortDept().equals("@")) {
				return 1;
			} else {

				int result = compareDept(o1,o2);
				if (result == 0)result = compareName(o1,o2);
				return result;
			}
		}

		return compareName(o1,o2);
	}

	public int compareDept(Contact o1, Contact o2)
	{
		if (o1.getSortDept().equals("@")
				|| o2.getSortDept().equals("#")) {
			return -1;
		} else if (o1.getSortDept().equals("#")
				|| o2.getSortDept().equals("@")) {
			return 1;
		} else {

			return o1.getSortDept().compareTo(o2.getSortDept());
		}
	}


	public int compareName(Contact o1, Contact o2)
	{
		if (o1.getSortLetters().equals("@")
				|| o2.getSortLetters().equals("#")) {
			return -1;
		} else if (o1.getSortLetters().equals("#")
				|| o2.getSortLetters().equals("@")) {
			return 1;
		} else {
			return o1.getSortLetters().compareTo(o2.getSortLetters());
		}
	}



}
