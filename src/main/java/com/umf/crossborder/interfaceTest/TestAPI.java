package com.umf.crossborder.interfaceTest;

import com.umf.crossborder.interfaceTest.main.MainTest;
import com.umf.crossborder.interfaceTest.utils.RSAUtil;
import org.junit.Test;

import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;


public class TestAPI {
	
	//#5.1.2 收汇申请
	@Test
	public void test_fileUpload() throws Exception{
		Map<String, Object> m = new HashMap<String, Object>();
		Map<String, Object> mm = new HashMap<String, Object>();
		m.put("notify_url","www.www.com");
		m.put("batch",mm);
		mm.put("file_name", "RFXS_3861_20170717_USD_170717R11.txt");
		mm.put("currency", "USD");
		mm.put("batch_date", "20170717");
		mm.put("batch_no", "170717R11");
		mm.put("file_path", "/");
		MainTest.httpPost(m, "3861", "http://fx.soopay.net/cbeweb_rest/receipt/batches");
	}
	
	//#3.2分拣结果查询
	@Test
	public void test_batchQuery() throws Exception{
		String id = "RB_GE3TANZRG5JDCML4GIYDCNZQG4YTOMRQGE3TCMJQG6VA";
		MainTest.httpGet("3861", "http://fx.soopay.net/cbeweb_rest/receipt/batches/"+id);
	}
	
	//#5.1.3确认付款
	@Test
	public void test_confirm() throws Exception{
		String id = "RB_GE3TANZRG5JDCML4GIYDCNZQG4YTOMRQGE3TCMJQG6VA";
		Map<String, Object> m = new HashMap<String, Object>();
		Map<String, Object> mm = new HashMap<String, Object>();
		Map<String, Object> mm2 = new HashMap<String, Object>();
		m.put("receipt_rows", 2);
		mm.put("currency", "USD");
		mm.put("total", 3.02);
		m.put("receipt_amount", mm);
		mm2.put("currency", "USD");
		mm2.put("total", 0);
		m.put("receipt_fee", mm2);
		MainTest.httpPost(m, "3861", "http://fx.soopay.net/cbeweb_rest/receipt/batches/"+id+"/instruction");
	}
	
	//#3.4收汇信息查询
	@Test
	public void test_queryIns() throws Exception{
		String id = "RB_GE3TANZRG5JDCML4GIYDCNZQG4YTOMRQGE3TCMJQG6VA";
		MainTest.httpGet("3861", "http://fx.soopay.net/cbeweb_rest/receipt/batches/"+id+"/instruction");
	}
	
	//#5.1.4 付款信息查询
	@Test
	public void test_queryPayment() throws Exception{
		String id = "RB_GE3TANZRG5JDCML4GIYDCNZQG4YTOMRQGE3TCMJQG6VA";
		String paymentNo = "69879919981";
		MainTest.httpGet("3861", "http://fx.soopay.net/cbeweb_rest/receipt/batches/"+id+"/payment/"+paymentNo);
	}
	
	//#3.6-1 对私协议号注册
	@Test
	public void test_registerAgreementForCustomer() throws Exception{
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("account_number", encryptByRsaPublic("6222888999333111"));
		m.put("account_holder_personal_number", encryptByRsaPublic("213698198512226359"));
		m.put("account_holder_personal_name", "测试");
		m.put("agreement_no", "123456789");
		m.put("agreement_type", "FORCUSTOMER");
		m.put("account_bin", "ABC");
		m.put("telephone", "10343055526");
		MainTest.httpPost(m, "3861", "http://fx.soopay.net/cbeweb_rest/receipt/agreements");
	}
	
	//#3.6-2对私协议号更新
	@Test
	public void test_updateAgreementForCustomer() throws Exception{
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("account_number", encryptByRsaPublic("6222888999333112"));
		m.put("account_holder_personal_number", encryptByRsaPublic("213698198512226359"));
		m.put("account_holder_personal_name", "测试");
		m.put("agreement_no", "123456789");
		m.put("agreement_type", "FORCUSTOMER");
		m.put("account_bin", "ABC");
		m.put("telephone", "10343055526");
		MainTest.httpPost(m, "3861", "http://fx.soopay.net/cbeweb_rest/receipt/agreements");
	}
	
	//#3.7-1对私协议协议号查血
	@Test
	public void test_queryAgreementForCustomer() throws Exception{
		String agreementNo = "123456789";
		MainTest.httpGet("3861", "http://fx.soopay.net/cbeweb_rest/receipt/agreements/"+agreementNo);
	}
	
	
	//#3.6-3对公协议号注册
	@Test
	public void test_registerAgreementForBusiness() throws Exception{
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("account_number", encryptByRsaPublic("6222888999333111"));
		m.put("account_holder_company_number", "250250250");
		m.put("account_holder_company_name", "测试公司");
		m.put("agreement_no", "123456788");
		m.put("agreement_type", "FORBUSINESS");
		m.put("account_bin", "ABC");
		m.put("telephone", "10343055526");
		m.put("company_contact_name", "张三");
		m.put("company_contact_email", "zhangsan@umpay.com");
		m.put("account_bank_branch_name", "帝都银行北师大分行");
		MainTest.httpPost(m, "3861", "http://fx.soopay.net/cbeweb_rest/receipt/agreements");
	}
	

	//#3.6-4对公协议号注册
	@Test
	public void test_updateAgreementForBusiness() throws Exception{
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("account_number", encryptByRsaPublic("6222888999333111"));
		m.put("account_holder_company_number", "250250250");
		m.put("account_holder_company_name", "测试公司");
		m.put("agreement_no", "123456788");
		m.put("agreement_type", "FORBUSINESS");
		m.put("account_bin", "ABC");
		m.put("telephone", "10343055526");
		m.put("company_contact_name", "张三");
		m.put("company_contact_email", "zhangsan@umpay.com");
		m.put("account_bank_branch_name", "帝都银行北师大分行");
		MainTest.httpPost(m, "3861", "http://fx.soopay.net/cbeweb_rest/receipt/agreements");
	}
	
	//#3.7-2对私协议协议号查血
	@Test
	public void test_queryAgreementForBusiness() throws Exception{
		String agreementNo = "123456788";
		MainTest.httpGet("3861", "http://fx.soopay.net/cbeweb_rest/receipt/agreements/"+agreementNo);
	}
	
	//#3.7协议号操作
	@Test
	public void test_operAgreement() throws Exception{
		String agreementNo = "123456788";
		MainTest.httpPost("{}", "3861", "http://fx.soopay.net/cbeweb_rest/receipt/agreements/"+agreementNo+"/close");
	}
	
	private static String  encryptByRsaPublic(String data) throws Exception{
		X509Certificate x509 = RSAUtil.getUMPCerInJar();
		PublicKey pk = x509.getPublicKey();
		return RSAUtil.encryptByRsaPublic(data, pk);
	}
	
}
