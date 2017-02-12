package task7.hasmukhi.admin.task7;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Admin on 2/8/2017.
 */

public class CustomList extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<Model>md;
    public CustomList(Context context, List<Model> md){
        this.context = context;
        this.md =md ;

    }

    @Override
    public int getCount() {
        return md.size();
    }

    @Override
    public Object getItem(int position) {
        return md.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(inflater == null)
            inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null)
            convertView =inflater.inflate(R.layout.list_student,null);

        TextView t1 = (TextView)convertView.findViewById(R.id.txtuserid);
        TextView t2 = (TextView)convertView.findViewById(R.id.txtid);
        TextView t3 = (TextView)convertView.findViewById(R.id.txttitle);
        TextView t4 = (TextView)convertView.findViewById(R.id.txtbody);


        Model m = md.get(position);
        t1.setText(String.valueOf(m.getUserid()));
        t2.setText(String.valueOf(m.getId()));
        t3.setText(m.getTitle());
        t4.setText(m.getBody());




        return convertView;


    }
}
