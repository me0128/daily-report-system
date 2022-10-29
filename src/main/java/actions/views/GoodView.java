package actions.views;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * いいね情報についてのViewモデル
 *
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GoodView {

	//Id
	private Integer id;

	//いいねした従業員id
	private EmployeeView employee;

	//日報id
	private ReportView report;

}
