package services;

import constants.JpaConst;

public class GoodService extends ServiceBase{

	/**
	 * いいねした従業員のデータ件数を取得し、返却する
	 */
	public long countAll() {
		long goods_count=(long)em.createNamedQuery(JpaConst.Q_GOOD_COUNT,Long.class)
				.getSingleResult();
		return goods_count;
	}
}
