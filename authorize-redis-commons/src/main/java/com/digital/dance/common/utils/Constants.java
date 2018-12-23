package com.digital.dance.common.utils;

public class Constants {

	/**
	 * 公共返回code
	 */
	public static final String RETURN_CODE_SUCCESS = new String("10000"); // 10000表示成功
	public static final String RETURN_CODE_FAILED = new String("10001"); // 10001表示不成
	public static final String SUCCESS_MSG = "SUCCESS";
	public static final String FAILED_MSG = "FAILED";
	/**
	 * 返回状码类型
	 */
	public static enum ReturnCode {
		/**
		 * 成功标识
		 */
		SUCCESS("10000"),
		/**
		 * 失败标识
		 */
		FAILURE("10001"),

		REDIRECT("10002"),

		NOPRIVILEGE("10003");

		private String code;

		private ReturnCode(String code) {
			this.code = code;
		}

		public String Code() {
			return this.code;
		}
	}

	/**
	 * 是否（工程中的所有用到是否尽量用这个
	 * 
	 */
	public static enum YesOrNo {
		/**
		 * 
		 */
		YES("Y"),
		/**
		 * 
		 */
		NO("N");

		private String code;

		private YesOrNo(String code) {
			this.code = code;
		}

		public String Code() {
			return this.code;
		}
	}
	/**
	 * 渠道标识
	 * 
	 */
	public static enum Channel {
		/**
		 * 门户
		 */
		web("20"),
		/**
		 * 
		 */
		app("10");
		
		private String code;
		
		private Channel(String code) {
			this.code = code;
		}
		
		public String Code() {
			return this.code;
		}
	}

	/**
	 * 场景标识
	 */
	public static enum SignFlag {
		/**
		 * 融资总览 - 总额
		 */
		FINANCE_TOTAL("00"),
		/**
		 * 融资总览 - 我的额度
		 */
		FINANCE_CREDIT("10"),
		/**
		 * 融资总览 - 我的贷款
		 */
		FINANCE_RESIDUAL("20"),
		/**
		 * 担保确认 - 双职工确
		 */
		GUAR_WORKGROUP("10"),
		/**
		 * 担保确认 - 员工互保
		 */
		GUAR_EMPLOYEE("20");
		private String code;

		private SignFlag(String code) {
			this.code = code;
		}

		public String Code() {
			return this.code;
		}
	}

	/**
	 * 客户身份类型
	 */
	public static enum CusType {
		/**
		 * 客户身份 - 个人
		 */
		CUS_PERSONAL("00"),
		/**
		 * 客户身份 - 企业
		 */
		CUS_COMPANY("01"),
		/**
		 * 客户身份 - 员工
		 */
		CUS_EMPLOYEE("11"),
		/**
		 * 客户身份 - 供应
		 */
		CUS_GYS("21"),
		/**
		 * 客户身份 - 售商
		 */
		CUS_XSS("22"),
		/**
		 * 客户身份 - 小微
		 */
		CUS_XW("25"),
		/**
		 * 其他对公客户
		 */
		CUS_COMPANY_OTHER("23"),
		/**
		 * 26-监管单位
		 */
		CUS_SUPERVISORY_UNIT("26"),
		/**
		 * 27-会员
		 */
		CUS_SUPERVISORY_MEMBER("27");

		private String code;

		private CusType(String code) {
			this.code = code;
		}

		public String Code() {
			return this.code;
		}
	}

	/**
	 * 操作类型
	 */
	public static enum OperatorType {
		/**
		 * 操作 - 同意/确认
		 */
		OPERATOR_CONFIRM("00"),
		/**
		 * 操作 - 拒绝/不同
		 */
		OPERATOR_REFUSED("10");

		private String code;

		private OperatorType(String code) {
			this.code = code;
		}

		public String Code() {
			return this.code;
		}
	}

	/**
	 * 消息类型
	 */
	public static enum MsgType {
		/**
		 * 消息类型 - 员工
		 */
		MSG_EMPLOYEE("10"),
		/**
		 * 消息类型 - 安德物流
		 */
		MSG_ANDE("20");

		private String code;

		private MsgType(String code) {
			this.code = code;
		}

		public String Code() {
			return this.code;
		}
	}

	/**
	 * 查询标识
	 */
	public static enum QueryType {
		/**
		 * 查询标识 - 期限利率
		 */
		PRD_DATE("10"),
		/**
		 * 查询标识 - 还款方式
		 */
		PRD_PAYWAY("20"),
		/**
		 * 查询标识 - 产品类型
		 */
		PRD_PRD_TYPE("30");

		private String code;

		private QueryType(String code) {
			this.code = code;
		}

		public String Code() {
			return this.code;
		}
	}

	/**
	 * 提额方式raiseWay
	 * 
	 */
	public static enum RaiseWay {
		/**
		 * 提收入证�?
		 */
		RAISEWAY_EARN("1"),
		/**
		 * 员工互保
		 */
		RAISEWAY_EACHOTHER("2"),
		/**
		 * 双职工担
		 */
		RAISEWAY_GUARANTEE("3");
		private String code;

		private RaiseWay(String code) {
			this.code = code;
		}

		public String Code() {
			return this.code;
		}
	}

	/**
	 * 授信申请提交错误
	 * 
	 */
	public static final class SaveLoanApplyType {
		public static String getMessage(String code) {
			if (code == null || "".equals(code)) {
				return "未知错误";
			}
			if ("01".equals(code)) {
				return "提交成功";
			}
			if ("02".equals(code)) {
				return "黑名单不准入";
			}
			if ("03".equals(code)) {
				return "红牌供应商不准入";
			}
			if ("04".equals(code)) {
				return "给出供应商不准";
			}
			if ("05".equals(code)) {
				return "付款币种非人民币不准";
			}
			if ("06".equals(code)) {
				return "hr状非正式不准";
			}
			if ("07".equals(code)) {
				return "在职时间不满足不准入";
			}
			if ("08".equals(code)) {
				return "职群无模型规则不准入 ";
			}
			if ("120".equals(code)) {
				return "授信策略配置有误";
			}
			if ("121".equals(code)) {
				return "通过流程方案编号未查询到相应的流程信";
			}
			if ("122".equals(code)) {
				return "已有正在处理的申";
			}
			if ("123".equals(code)) {
				return "模型编号不能为空";
			}
			if ("124".equals(code)) {
				return "未查询到指标模型";
			}
			if ("125".equals(code)) {
				return "已有美薪";
			}
			if ("126".equals(code)) {
				return "已为别人做过美薪贷双职工担保";
			}
			if ("127".equals(code)) {
				return "担保人已有美薪贷";
			}
			if ("128".equals(code)) {
				return "担保人已为别人做过双职工担保";
			}
			if ("220".equals(code)) {
				return "客户ID不能为空";
			}
			if ("221".equals(code)) {
				return "客户名称不能为空";
			}
			if ("222".equals(code)) {
				return "MIP账号不能为空";
			}
			if ("223".equals(code)) {
				return "证件号码不能为空";
			}
			if ("224".equals(code)) {
				return "证件类型不能为空";
			}
			if ("225".equals(code)) {
				return "业务线不能为空";
			}
			if ("226".equals(code)) {
				return "联系方式不能为空";
			}
			if ("227".equals(code)) {
				return "产品编号不能为空";
			}
			if ("228".equals(code)) {
				return "产品名称不能为空";
			}
			if ("229".equals(code)) {
				return "币种不能为空";
			}
			if ("230".equals(code)) {
				return "客户类别不能为空";
			}
			if ("231".equals(code)) {
				return "申请渠道不能为空";
			}
			if ("105".equals(code)) {
				return "适配的业务逻辑场景不能为空";
			}
			if ("106".equals(code)) {
				return "未查询到对应的业务逻辑处理";
			}
			if ("107".equals(code)) {
				return "适配的指标模型场景不能为空";
			}
			if ("108".equals(code)) {
				return "未查询到对应的指标模型场景";
			}

			return "未知错误";
		}

	}

	/**
	 * 提额申请
	 * 
	 */
	public static final class AddLoanLimit {
		public static String getMessage(String code) {
			if (code == null || "".equals(code)) {
				return "未知错误";
			}
			if ("001".equals(code)) {
				return "可以提额,但只能做员工互保或双职工";
			}
			if ("002".equals(code)) {
				return "提额的授信额度不能超过原授信额度1.5";
			}
			if ("003".equals(code)) {
				return "您选择的担保人因没有美薪贷的授信记录，无法为您提供担保";
			}
			if ("004".equals(code)) {
				return "您选择的担保人因美薪贷的剩余额度不足，无法为您提供担保";
			}
			if ("006".equals(code)) {
				return "双职工担保，担保人存在申请中的美薪贷或有效的美薪";
			}
			if ("008".equals(code)) {
				return "无该职工信息";
			}
			if ("OO".equals(code)) {
				return "您的职级不符合准入条";
			}
			if ("O".equals(code)) {
				return "您的入职时间小于3年，不符合提额条";
			}
			if ("p".equals(code)) {
				return "您的入职时间小于半年，不符合提额条件";
			}
			return "未知错误";
		}

	}

	/**
	 * MarrStatus 婚姻状
	 * 
	 */
	public static enum MarrStatus {
		/**
		 * 已婚
		 */
		MARRSTATUS_Y("1"),
		/**
		 * 未婚
		 */
		MARRSTATUS_N("2");

		private String code;

		private MarrStatus(String code) {
			this.code = code;
		}

		public String Code() {
			return this.code;
		}
	}

	/**
	 * 产品条线 YG-员工 XW-小微 GY-供应 NB-成员单位 XS-售链
	 */
	public static enum PrdLine {
		/**
		 * 员工
		 */
		YG("YG"),
		/**
		 * 小微
		 */
		XW("XW"),
		/**
		 * 供应
		 */
		GY("GY"),
		/**
		 * 成员单位
		 */
		NB("NB"),
		/**
		 * 售链
		 */
		XS("XS");
		private String code;

		private PrdLine(String code) {
			this.code = code;
		}

		public String Code() {
			return this.code;
		}
	}
	public static String RESOURCE_SERVER_NAME = "chapter17-server";
	public static final String INVALID_CLIENT_DESCRIPTION = "客户端验证失败，如错误的client_id/client_secret。";

}
