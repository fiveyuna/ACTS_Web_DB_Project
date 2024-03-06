package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.user.*;
import controller.post.*;
import controller.review.UploadReviewController;
import controller.review.UploadReviewFormController;
import controller.transaction.*;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // 揶쏉옙 占쎌뒄筌ｏ옙 uri占쎈퓠 占쏙옙占쎈립 controller 揶쏆빘猿쒐몴占� 占쏙옙占쎌삢占쎈막 HashMap 占쎄문占쎄쉐
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	// 揶쏉옙 uri占쎈퓠 占쏙옙占쎌벓占쎈┷占쎈뮉 controller 揶쏆빘猿쒐몴占� 占쎄문占쎄쉐 獄쏉옙 占쏙옙占쎌삢
    	
    	//�룯�뜃由곤옙�넅筌롳옙 獄쏉옙 嚥≪뮄�젃占쎌뵥�⑨옙 嚥≪뮄�젃占쎈툡占쎌뜍
        mappings.put("/", new ForwardController("index.jsp")); //野껋럩�뜚: �룯�뜃由곤옙�넅筌롫똻占� index.jsp嚥∽옙 占쎌뵠占쎈짗占쎈릭野껓옙
        //�룯�뜃由�-筌롫뗄�뵥占쎌넅筌롳옙
        mappings.put("/comm/main", new ListPostController());
//        mappings.put("/comm/search", new ForwardController("/comm/search.jsp"));
//        mappings.put("/comm/search/searchForm", new ForwardController("/comm/searchForm.jsp"));
        mappings.put("/comm/search/list", new SearchCategoryController());
        mappings.put("/user/myPage", new MyPageController());
        mappings.put("/user/login/form", new ForwardController("/user/loginForm.jsp")); //野껋럩�뜚: 嚥≪뮄�젃占쎌뵥 甕곌쑵�뱣占쎌뱽 占쎈듇�몴���늺 /user/loginForm.jsp嚥∽옙 占쎌뵠占쎈짗占쎈릭野껓옙
        mappings.put("/user/login", new LoginController());
        mappings.put("/user/logout", new LogoutController());
        mappings.put("/user/view", new ViewUserController());
        mappings.put("/comm/buyerPage", new BuyerPostListController());       
        
        mappings.put("/user/list", new ListUserController()); //野껋럩�뜚: 占쎌�占쏙옙�뵳�딅뮞占쎈뱜 癰귣��뮉椰꾬옙 占쎄텢占쎌뒠占쎌쁽揶쏉옙 占쎈툡占쎈빒 administrator揶쏉옙 癰귣��뮉椰꾧퀡�뵬占쎄퐣 筌먲옙
      
        
        
        // 占쎌돳占쎌뜚 揶쏉옙占쎌뿯 占쎈쨲 占쎌뒄筌ｏ옙�⑨옙 揶쏉옙占쎌뿯 占쎌뒄筌ｏ옙 筌ｌ꼶�봺 癰귣쵑鍮� (占쎈쨲占쎈퓠 �뚣끇占썬끇�빍占쎈뼒 占쎄퐨占쎄문 筌롫뗀�뤀 �빊遺쏙옙�몴占� 占쎌맄占쎈맙)
        mappings.put("/user/register/form", new ForwardController("/user/registerForm.jsp"));
        mappings.put("/user/register", new RegisterUserController());
        
        //留덉씠�럹�씠吏� 愿�由�
        mappings.put("/user/myInfo", new MyInfoController()); //寃쎌썝: �궡�젙蹂대줈 媛��뒗 而⑦듃濡ㅻ윭

        mappings.put("/user/update", new UpdateUserController());
        mappings.put("/user/myPost", new MyPostController());

        mappings.put("/user/myPage/myBuyerTransaction", new MyBuyerTransactionListController());
        mappings.put("/user/myPage/mySellerTransaction", new MySellerTransactionListController());
    
        mappings.put("/user/followingList", new FavoriteListController());
        
        mappings.put("/user/delete", new DeleteUserController());
        
        // 野껊슣�뻻疫뀐옙 �꽴占썹뵳占�
        mappings.put("/post/upload/form", new UploadPostFormController()); //野껊슣�뻻疫뀐옙 占쎌삂占쎄쉐 占쎈쨲 占쎌뒄筌ｏ옙(嚥≪뮄�젃占쎌뵥 占쎈툡占쎌뵠占쎈탵 占쎌젟癰귨옙 占쎄퐜繹먲옙->writerId嚥∽옙 占쎈쾺疫꿸퀣�맄占쎈맙)
        mappings.put("/post/upload", new UploadPostController());
        mappings.put("/post/update", new UpdatePostController());
        mappings.put("/post/postInfo", new PostInfoController());
        mappings.put("/post/sellerPostUpdate", new SellerUpdatePostController());
        mappings.put("/post/sellerPostInfo", new SellerPostInfoController());
        mappings.put("/post/delete", new DeletePostController());
        mappings.put("/post/search", new PostSearchController());

        mappings.put("/post/transactionForm", new CreateTransactionFormController());
        mappings.put("/post/transaction", new CreateTransactionController());
        mappings.put("/user/transactionInfo", new TransactionInfoController());
        mappings.put("/user/transactionDelete", new DeleteTransactionController());
        logger.info("Initialized Request Mapping!");
        
        // 占쎈솊疫뀐옙 �꽴占썹뵳占�
        mappings.put("/review/upload/form", new UploadReviewFormController());
        mappings.put("/review/upload", new UploadReviewController());  
    }

    public Controller findController(String uri) {	
    	// 雅뚯눘堉깍쭪占� uri占쎈퓠 占쏙옙占쎌벓占쎈┷占쎈뮉 controller 揶쏆빘猿쒐몴占� 筌≪뼚釉� 獄쏆꼹�넎
        return mappings.get(uri);
    }
}
