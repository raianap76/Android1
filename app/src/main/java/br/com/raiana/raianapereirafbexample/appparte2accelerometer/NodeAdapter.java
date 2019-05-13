package br.com.raiana.raianapereirafbexample.appparte2accelerometer;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class NodeAdapter extends RecyclerView.Adapter<NodeAdapter.NodeViewHolder> implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mAcelerometro;
    public TextView teste;
    private float coord;


    float [] history = new float[2];


    public static RecyclerInterface recyclerInterface;
    static Context context;
    private List<Node> nodeList;

    public NodeAdapter(Context ctx, List<Node> list, RecyclerInterface clickRecyclerInterface){
        this.context = ctx;
        this.nodeList = list;
        this.recyclerInterface = clickRecyclerInterface;
    }

    @Override
    public void onBindViewHolder(NodeViewHolder holder, final int i){


        Node no = nodeList.get(i);
        holder.txtNodeName.setText(no.getIdNode());
        holder.btnAdd.setOnClickListener(new View.OnClickListener(){
            public void onClick (View V){
                Context ctx = NodeAdapter.context;
                NodeHelper helper = new NodeHelper(ctx);
                Node selectedNode = nodeList.get(i);
                int currentNodes = selectedNode.getChildren().size();
                String childNodeID = selectedNode.getIdNode() + "." + (currentNodes+1);
                String childNodeContent = helper.showInsertDialog(selectedNode, childNodeID);
            }
        });
        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context ctx = NodeAdapter.context;
                NodeHelper helper = new NodeHelper(ctx);
                Node selectedNode = nodeList.get(i);
                helper.showUpdateDialog(selectedNode);
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Node selectedNode = nodeList.get(i);
                nodeList.remove(selectedNode);
                notifyItemRemoved(i);
                notifyDataSetChanged();
            }
        });

        float teste =  this.coord;
        System.out.print(teste);

        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);//• Serviço do Sistema que gerencia os sensores Para obter as instâncias dos sensores
        mAcelerometro = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);//Para acessar um sensor específico

        mSensorManager.registerListener(this, mAcelerometro, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public NodeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.node_item,
                viewGroup, false);
        return new NodeViewHolder(itemView);
    }



    @Override
    public int getItemCount(){
        return nodeList.size();
    }

    protected class NodeViewHolder extends RecyclerView.ViewHolder {
        protected TextView txtNodeName;
        protected ImageButton btnAdd;
        protected ImageButton btnUpdate;
        protected ImageButton btnDelete;


        public NodeViewHolder(final View itemView){
            super(itemView);
            txtNodeName = (TextView) itemView.findViewById(R.id.txtNodeName);
            btnAdd = (ImageButton) itemView.findViewById(R.id.btnNodeAdd);
            btnUpdate = (ImageButton) itemView.findViewById(R.id.btnNodeUpdate);
            btnDelete = (ImageButton) itemView.findViewById(R.id.btnNodeDelete);
            teste = (TextView) itemView.findViewById(R.id.textView);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    recyclerInterface.onItemClick(nodeList.get(getLayoutPosition()));
                }
            });
        }
    }
    //Movimentos de cima para baixo faz com que os nós sejam deletados
    @Override
    public void onSensorChanged(SensorEvent event) {
        float xChange = history[0] - event.values[0];
        float yChange = history[1] - event.values[1];


        history[0] = event.values[0];
        history[1] = event.values[1];

        final int i=0;
        if (xChange > 2){
            //  direction[0] = "LEFT";
        }
        else if (xChange < -2){
            //  direction[0] = "RIGHT";

        }

        if (yChange > 2){
            //"DOWN";
            //deletar somente se tiver nós na arvore

            if(nodeList.get(i)!= null) {
                Node selectedNode = nodeList.get(i);
                nodeList.remove(selectedNode);
                notifyItemRemoved(i);
                notifyDataSetChanged();
            }

            teste.setText(String.valueOf(yChange));

        }
        else if (yChange < -2){
            //"UP";
            teste.setText(String.valueOf(yChange));
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
