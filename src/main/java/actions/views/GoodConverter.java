package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Good;

/**
 * いいねデータのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class GoodConverter {

	/**
	 * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
	 */
	public static Good toModel(GoodView gv) {
		return new Good(
				gv.getId(),
				ReportConverter.toModel(gv.getReport()),
				EmployeeConverter.toModel(gv.getEmployee()));

	}

	/**
	* DTOモデルのインスタンスからViewモデルのインスタンスを作成する
	*/
	public static GoodView toView(Good g) {

		if (g == null) {
			return null;

		}
		return new GoodView(
				g.getId(),
				ReportConverter.toView(g.getReport()),
				EmployeeConverter.toView(g.getEmployee()));
	}

	/**
	 * DTOモデルのリストからViewモデルのリストを作成する
	 */
	public static List<GoodView> toViewList(List<Good> list) {
		List<GoodView> evs = new ArrayList<>();

		for (Good g : list) {
			evs.add(toView(g));
		}
		return evs;
	}
	/**
	 * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
	 */
	public static void copyViewToModel(Good g, GoodView gv) {
		g.setId(gv.getId());
		g.setReport(ReportConverter.toModel(gv.getReport()));
		g.setEmployee(EmployeeConverter.toModel(gv.getEmployee()));

	}
}
