package TestCases;
    RealmAPI realmAPI = new RealmAPI();
    private String url = "http://recruit01.test01.brighttalk.net:8080/user/realm";
    private String id = "";
    private String random = realmAPI.randomStringWithNo(5);
    private String name = "Test RealM - "+random;
    private String description = "Test description_"+random;

    @Test(priority = 1)
    public void CreateRealmAPIPost() throws IOException {
        String ExpectedString = realmAPI.GetResponseForPOSTCallForCreateRealm(url,name,description);
        id = ExpectedString.substring(ExpectedString.indexOf("?><realm id=\"")+13,ExpectedString.indexOf("?><realm id=\"")+17);
        System.out.println("id is = "+id);
        try{
            System.out.println("FAIL: Realm Id is not generated..");
    }    

    @Test(priority = 2)
    public void DuplicateCreateRealmAPI() throws IOException {
        String ExpectedString = realmAPI.GetResponseForPOSTCallToCreateRealm(url,name,description);
    }
    @Test(priority = 3)
    @Test(priority = 6)
    public void GetRealmAPI() throws IOException {
    	System.out.println("=============================Test Case 6=====================");
        String ExpectedString = realmAPI.GetResponseForGETCall(url1);
        Document doc = Jsoup.connect(url1).get();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(doc.toString().getBytes())));
        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = reader.readLine()) != null) {
            result.append(line.trim());
        }
        Assert.assertEquals(result.toString(),ExpectedString.toString(),"FAIL: The Responses does not matches.");
        System.out.println("PASS: Get Realm Responses Matches.");
    }

    @Test(priority = 7)
}