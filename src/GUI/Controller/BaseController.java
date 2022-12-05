package src.GUI.Controller;

import src.GUI.Model.MyTunesModel;

public abstract class BaseController {

    private MyTunesModel model;

    public void setModel(MyTunesModel model) {
        this.model = model;
    }

    public MyTunesModel getModel() throws Exception {
        return new MyTunesModel();
    }

    public abstract void setup() throws Exception;
}
