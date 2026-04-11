package up.l3info.LostKnight.view;

import java.util.List;
import up.l3info.LostKnight.mvc.View;

public class AppTextualView implements View {

    private AppTextualView() {}

    public static AppTextualView create(List<View> subViews) {
        return new AppTextualView();
    }

    @Override public void hide() {}
    @Override public void show() {}
}
