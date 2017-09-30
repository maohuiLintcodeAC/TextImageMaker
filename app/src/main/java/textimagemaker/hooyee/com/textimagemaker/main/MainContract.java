package textimagemaker.hooyee.com.textimagemaker.main;

/**
 * Created by Hooyee on 2017/9/30.
 */

public interface MainContract {
    interface Presenter {

    }

    interface View {
        String getTextContent();

        void clearContent();
    }
}
