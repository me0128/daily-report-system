package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Good;

/**
 * いいねデータのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class GoodConverter {

	public static Good toModel(GoodView gv) {
		return new Good(
				EmployeeConverter.toModel(gv.getEmployee()),
				ReportConverter.toModel(gv.getReport()));

	}

	public static GoodView toView(Good g) {

		if (g == null) {
			return null;

		}
		return new GoodView(
				EmployeeConverter.toView(g.getEmployee()),
				ReportConverter.toView(g.getReport()));
	}

	public static List<GoodView> toViewList(List<Good> list) {
		List<GoodView> evs = new ArrayList<>();

		for (Good g : list) {
			evs.add(toView(g));
		}
		return evs;
	}

	public static void copyViewToModel(Good g, GoodView gv) {
		g.setEmployee(EmployeeConverter.toModel(gv.getEmployee()));
		g.setReport(ReportConverter.toModel(gv.getReport()));
	}
}
