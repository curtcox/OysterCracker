package ios.uilist;

import org.robovm.apple.uikit.UITableViewController;
import org.robovm.apple.uikit.UITableViewDataSource;
import x.event.Action;
import x.uilist.IXListCell;

final class IosUIList<T>
    extends UITableViewController
{
    private IosBasicListCellRenderer renderer;
    private UITableViewDataSource model;

    private IosUIList(UITableViewDataSource model) {
        this.model = model;
        getTableView().setDataSource(model);
    }

    public void addActionListener(Action.Listener listener) {

    }

    public int getSelectedIndex() {
        return 0;
    }

    public void setRenderer(IosBasicListCellRenderer renderer) {
        this.renderer = renderer;
    }

    public static IosUIList of(UITableViewDataSource model) {
        return new IosUIList(model);
    }

    public static IosUIList of(UITableViewDataSource model,IXListCell.ConfigProducer configurer) {
        IosUIList list = IosUIList.of(model);
        list.setRenderer(new IosBasicListCellRenderer(configurer));
        return list;
    }

    public void reloadData() {
        getTableView().reloadData();
    }
}
