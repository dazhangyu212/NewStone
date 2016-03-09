package com.stone.ordering.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
*类名：SysUserModel
*描述：
*公司:北京海鑫科金高科技股份有限公司
*作者：yuchengkuan
*版本：V1.0
*创建时间：2015年10月15日
*最后修改时间：2015年10月15日
*/
@DatabaseTable(tableName="sys_user")
public class SysUserModel {
//	  `ID`              VARCHAR(32) not null,
//	  `USERNAME`        VARCHAR(50),
//	  `PASSWORD`        VARCHAR(50),
//	  `TRUE_NAME`       VARCHAR(100),
//	  `UNIT_CODE`       VARCHAR(12),
//	  `UNIT_NAME`       VARCHAR(100),
//	  `POLICE_ID`       VARCHAR(6),
//	  `OPEN_FLAG`       VARCHAR(4),
//	  `REMARK`          VARCHAR(4000),
//	  `CREATE_USER`     VARCHAR(50),
//	  `CREATE_DATETIME` datetime DEFAULT NULL,
//	  `UPDATE_USER`     VARCHAR(50),
//	  `UPDATE_DATETIME` datetime DEFAULT NULL,
//	  `REV1`            VARCHAR(100),
//	  `REV2`            VARCHAR(100),
//	  `REV3`            VARCHAR(100),
//	  `REV4`            VARCHAR(100),
//	  `REV5`            VARCHAR(100),
//	  `REV6`            VARCHAR(100),
//	  `REV7`            VARCHAR(100),
//	  `REV8`            VARCHAR(100)
	
	/**
	 * 主键
	 */
	@DatabaseField(columnName="ID" , id = true)
	private String id;
	/**
	 * 用户名
	 */
	@DatabaseField(columnName="USERNAME")
	private String username;
	/**
	 * 密码
	 */
	@DatabaseField(columnName="PASSWORD")
	private String password;
	/**
	 * 真名
	 */
	@DatabaseField(columnName="TRUE_NAME")
	private String trueName;
	/**
	 * 单位代码
	 */
	@DatabaseField(columnName="UNIT_CODE")
	private String unitCode;
	/**
	 * 单位名称
	 */
	@DatabaseField(columnName="UNIT_NAME")
	private String unitName;
	/**
	 * 警号
	 */
	@DatabaseField(columnName="POLICE_ID")
	private String policeId;
	/**
	 * 开启标识
	 */
	@DatabaseField(columnName="OPEN_FLAG")
	private String openFlag;
	/**
	 * 备注
	 */
	@DatabaseField(columnName="REMARK")
	private String remark;
	/**
	 * 创建人
	 */
	@DatabaseField(columnName="CREATE_USER")
	private String createUser;
	/**
	 * 创建时间
	 */
	@DatabaseField(columnName="CREATE_DATETIME")
	private String createDateTime;
	/**
	 * 修改人
	 */
	@DatabaseField(columnName="UPDATE_USER")
	private String updateUser;
	/**
	 * 修改时间
	 */
	@DatabaseField(columnName="UPDATE_DATETIME")
	private String updateDateTime;
	/**
	 * 备用字段1
	 */
	@DatabaseField(columnName="REV1")
	private String rev1;
	/**
	 * 备用字段2
	 */
	@DatabaseField(columnName="REV2")
	private String rev2;
	/**
	 * 备用字段3
	 */
	@DatabaseField(columnName="REV3")
	private String rev3;
	/**
	 * 备用字段4
	 */
	@DatabaseField(columnName="REV4")
	private String rev4;
	/**
	 * 备用字段5
	 */
	@DatabaseField(columnName="REV5")
	private String rev5;
	/**
	 * 备用字段6
	 */
	@DatabaseField(columnName="REV6")
	private String rev6;
	/**
	 * 备用字段7
	 */
	@DatabaseField(columnName="REV7")
	private String rev7;
	/**
	 * 备用字段8
	 */
	@DatabaseField(columnName="REV8")
	private String rev8;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return username;
	}
	public void setUserName(String userName) {
		this.username = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getUnitCode() {
		return unitCode;
	}
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getPoliceId() {
		return policeId;
	}
	public void setPoliceId(String policeId) {
		this.policeId = policeId;
	}
	public String getOpenFlag() {
		return openFlag;
	}
	public void setOpenFlag(String openFlag) {
		this.openFlag = openFlag;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getUpdateDateTime() {
		return updateDateTime;
	}
	public void setUpdateDateTime(String updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
	public String getRev1() {
		return rev1;
	}
	public void setRev1(String rev1) {
		this.rev1 = rev1;
	}
	public String getRev2() {
		return rev2;
	}
	public void setRev2(String rev2) {
		this.rev2 = rev2;
	}
	public String getRev3() {
		return rev3;
	}
	public void setRev3(String rev3) {
		this.rev3 = rev3;
	}
	public String getRev4() {
		return rev4;
	}
	public void setRev4(String rev4) {
		this.rev4 = rev4;
	}
	public String getRev5() {
		return rev5;
	}
	public void setRev5(String rev5) {
		this.rev5 = rev5;
	}
	public String getRev6() {
		return rev6;
	}
	public void setRev6(String rev6) {
		this.rev6 = rev6;
	}
	public String getRev7() {
		return rev7;
	}
	public void setRev7(String rev7) {
		this.rev7 = rev7;
	}
	public String getRev8() {
		return rev8;
	}
	public void setRev8(String rev8) {
		this.rev8 = rev8;
	}
}

