package services;

import java.util.List;

import javax.persistence.NoResultException;

import actions.views.EmployeeConverter;
import actions.views.EmployeeView;
import actions.views.GoodConverter;
import actions.views.GoodView;
import actions.views.ReportConverter;
import actions.views.ReportView;
import constants.JpaConst;
import models.Good;

//いいねテーブルの操作に関わる処理を行うクラス

public class GoodService extends ServiceBase {

	/**
	 * 指定されたページ数の一覧画面に表示するデータを取得し、GoodViewのリストで返却する
	 * @param page ページ数
	 * @return 表示するデータのリスト
	 */
	public List<GoodView> getMinePerPage(ReportView report, int page) {

		List<Good> goods = em.createNamedQuery(JpaConst.Q_GOOD_GET_ALL_MINE, Good.class)
				.setParameter(JpaConst.JPQL_PARM_REPORT, ReportConverter.toModel(report))
				.setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
				.setMaxResults(JpaConst.ROW_PER_PAGE)
				.getResultList();
		return GoodConverter.toViewList(goods);
	}

	//日報idと従業員idを元にいいねデータ取得
	public GoodView getGoodbyReportAndEmployee(EmployeeView employee, ReportView report) {
		Good good = null;
		try {
			good = em.createNamedQuery(JpaConst.Q_GOOD_GET_REPORT_AND_EMPLOYEE, Good.class)
					.setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(employee))
					.setParameter(JpaConst.JPQL_PARM_REPORT, ReportConverter.toModel(report))
					.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
		return GoodConverter.toView(good);
	}

	/**
	* 指定した日報のいいね数（従業員id)の件数を取得し、返却する
	* @param employee
	* @return いいね（従業員id）の件数
	*/
	public long countAllMine(ReportView report) {
		long count = (long) em.createNamedQuery(JpaConst.Q_GOOD_COUNT_ALL_MINE, Long.class)
				.setParameter(JpaConst.JPQL_PARM_REPORT, ReportConverter.toModel(report))
				.getSingleResult();
		return count;
	}

	/**
	 * いいねした従業員のデータ件数を取得し、返却する
	 */
	public long countAll() {
		long goods_count = (long) em.createNamedQuery(JpaConst.Q_GOOD_COUNT, Long.class)
				.getSingleResult();
		return goods_count;
	}

	/**
	 * idを条件に取得したデータをGoodViewのインスタンスで返却する
	 * @param id
	 * @return 取得データのインスタンス
	 */
	public GoodView findOne(int id) {
		Good good = findOneInternal(id);
        return GoodConverter.toView(good);
	}

	/**
	 * 画面ボタン押下後、日報IDとログイン中従業員IDをいいねテーブルに登録
	 * @return
	 */

	public void create(GoodView gv) {
		createInternal(gv);

	}

	/**
	 ボタン押下後、テーブルよりいいね削除
	 */
	public void destroy(Integer id) {

		//idを条件に登録済みのいいね情報を取得する
		GoodView goodDel = findOne(id);

		destroyInternal(goodDel);
	}

	/**
	    * 社員番号を条件に該当するデータの件数を取得し、返却する
	    * @param code 社員番号
	    * @return 該当するデータの件数
	    */
	public long countByCode(String code) {

		//指定した社員番号を保持する従業員の件数を取得
		long employees_count = (long) em.createNamedQuery(JpaConst.Q_GOOD_COUNT_REGISTERED_BY_CODE, Long.class)
				.setParameter(JpaConst.JPQL_PARM_CODE, code)
				.getSingleResult();
		return employees_count;
	}

	/**
	 * idを条件にデータを1件取得する
	 * @param id
	 * @return 取得データのインスタンス
	 */
	private Good findOneInternal(int id) {
		Good good=em.find(Good.class, id);
		return good;
	}

	/**
	    * いいねデータを1件登録する
	    * @param rv 日報データ
	    */
	private void createInternal(GoodView gv) {

		em.getTransaction().begin();
		em.persist(GoodConverter.toModel(gv));
		em.getTransaction().commit();

	}

	/**
	 いいねを削除する
	 */
	private void destroyInternal(GoodView gv) {

		em.getTransaction().begin();
		em.remove(GoodConverter.toModel(gv)); // データ削除
		em.getTransaction().commit();

	}

}
