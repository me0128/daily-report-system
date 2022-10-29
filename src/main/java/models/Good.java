package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@NamedQueries({
		@NamedQuery(name = JpaConst.Q_GOOD_GET_ALL, query = JpaConst.Q_GOOD_GET_ALL_DEF),
		@NamedQuery(name = JpaConst.Q_GOOD_COUNT, query = JpaConst.Q_GOOD_COUNT_DEF),
		@NamedQuery(name = JpaConst.Q_GOOD_GET_ALL_MINE, query = JpaConst.Q_GOOD_GET_ALL_MINE_DEF),
		@NamedQuery(name = JpaConst.Q_GOOD_COUNT_ALL_MINE, query = JpaConst.Q_GOOD_COUNT_ALL_MINE_DEF),
		@NamedQuery(name = JpaConst.Q_GOOD_GET_REPORT_AND_EMPLOYEE, query = JpaConst.Q_GOOD_GET_REPORT_AND_EMPLOYEE_DEF)
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Good {

	//id
	@Id
	@Column(name = JpaConst.GOOD_COL_ID)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	//いいねした日報のid
	@ManyToOne
	@JoinColumn(name = JpaConst.GOOD_COL_REP, nullable = false)
	private Report report;

	//いいねした従業員id
	@ManyToOne
	@JoinColumn(name = JpaConst.GOOD_COL_EMP, nullable = false)
	private Employee employee;
}
