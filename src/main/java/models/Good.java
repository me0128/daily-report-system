package models;

//import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * GOODデータのDTOモデル
 *
 */
@Table(name = JpaConst.TABLE_GOOD)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Good {

	//いいねした従業員id
	@ManyToOne
	@JoinColumn(name = JpaConst.GOOD_COL_EMP, nullable = false)
	private Employee employee;

	//いいねした日報のid
	@ManyToOne
	@JoinColumn(name = JpaConst.GOOD_COL_REP, nullable = false)
	private Report report;
}
