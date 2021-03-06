package com.example.jesus.examendamian;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.jesus.examendamian.dummy.DummyContent;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.content);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);
       //creamos el boton  y lo asociamos con su id
        Button button = (Button) rootView.findViewById(R.id.button);
               button.setOnClickListener(new View.OnClickListener() {
                   @Override
                             //creo el metodo onClick para cuando se pulse el boton
                            //cerraremos el fragment cuando no este en pantalla
                   public void onClick(View v) {
                       ItemListFragment frag = (ItemListFragment) getFragmentManager().findFragmentById(R.id.item_list);//Recogemos el fragment de la lista



                       if (frag == null || !frag.isInLayout()) {
                          //creo un nuevo intent llamado intResultado , mando el resultado y cierro la activity
                           Intent intResultado = new Intent();
                           intResultado.putExtra("", "Cerrando");
                           getActivity().setResult(Activity.RESULT_OK,intResultado);

                            //esta linea ya estaba pero es la que me cierra la activity
                           getActivity().finish();
                       } else {
                           //con la siguiente linea dejaremos en blanco el TextView
                           ((TextView) rootView.findViewById(R.id.item_detail)).setText("");
                       }

                   }
                           });


        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.item_detail)).setText(mItem.details);
        }

        return rootView;
    }
}
