package commonLibs.api;

import commonLibs.utils.CommonFunc;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class GetContactsAPI extends BaseAPI {

    CommonFunc commonFunc=new CommonFunc();

    public Response getContactId(int id) {

        Response res=given().get(contactsUrl+"/"+id);
        commonFunc.checkUserInfo(res, commonFunc.user());
        commonFunc.checkUrl(res,id);
        return res;
    }

    public Response getContacts() {

        Response res=given().get(contactsUrl);
        return res;
    }


    public void getFindContact(String fs) {

        String[][] keyvalue=commonFunc.stringParser(fs);

        Response resp=given().get(contactsUrl+"?"+fs);

        int arrlength=keyvalue.length;
        for (int i=0;i<arrlength;i++){
            String val = resp.jsonPath().get("data.info[0]."+keyvalue[i][0]);
            val.contains(keyvalue[i][1]);
        }
        int statusCode = resp.getStatusCode();
        Assert.assertEquals(statusCode, 200);

    }
}
