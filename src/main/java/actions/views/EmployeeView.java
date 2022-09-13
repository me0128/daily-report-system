package actions.views;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//従業員情報について画面の入力値・出力値を扱うViewモデル

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeView {

    private Integer id;

    private String code;

    private String name;

    private String password;

    private Integer adminFlag;//管理者権限があるかどうか

    private LocalDateTime createdAt;//登録日時

    private LocalDateTime updatedAt;//更新日時

    private Integer deleteFlag;//削除された従業員か（現0，削除1）
}
