package c1.uilist;

import com.codename1.ui.list.ContainerList;
import com.codename1.ui.list.ListModel;

final class UIContainerList<T>
    extends ContainerList 
    implements IList
{
    UIContainerList(ListModel model) {
        super(model);
    }

    public void setCellRenderer(BasicListCellRenderer renderer) {
        super.setRenderer(renderer);
    }
}
