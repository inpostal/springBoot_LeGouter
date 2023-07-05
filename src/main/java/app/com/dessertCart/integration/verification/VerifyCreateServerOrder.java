package app.com.dessertCart.integration.verification;

import app.com.dessertCart.integration.domain.CreateServerOrderObj;
import app.com.dessertCart.integration.ecpayOperator.PaymentVerifyBase;
import app.com.dessertCart.integration.errorMsg.ErrorMessage;
import app.com.dessertCart.integration.exception.EcpayException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.lang.reflect.Method;

public class VerifyCreateServerOrder extends PaymentVerifyBase {

    public VerifyCreateServerOrder() {
        super();
    }

    public String getAPIUrl(String mode) {
        Element ele = (Element) doc.getElementsByTagName("CreateServerOrder").item(0);
        String url = "";
        NodeList nodeList = ele.getElementsByTagName("url");
        for (int i = 0; i < nodeList.getLength(); i++) {
            ele = (Element) nodeList.item(i);
            if (ele.getAttribute("type").equalsIgnoreCase(mode)) {
                url = ele.getTextContent();
                break;
            }
        }
        if (url == "") {
            throw new EcpayException(ErrorMessage.OperatingMode_ERROR);
        }
        return url;
    }

    public void verifyParams(CreateServerOrderObj obj) {
        Class<?> cls = obj.getClass();
        Method method;
        String objValue;
        Element ele = (Element) doc.getElementsByTagName("CreateServerOrder").item(0);
        NodeList nodeList = ele.getElementsByTagName("param");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element tmpEle = (Element) nodeList.item(i);

            try {
                method = cls.getMethod("get" + tmpEle.getAttribute("name"), null);
                objValue = method.invoke(obj, null).toString();
            } catch (Exception e) {
                throw new EcpayException(ErrorMessage.OBJ_MISSING_FIELD);
            }
//			requireCheck(tmpEle.getAttribute("name"), objValue, tmpEle.getAttribute("require").toString());
            valueCheck(tmpEle.getAttribute("type"), objValue, tmpEle);
        }
    }
}
