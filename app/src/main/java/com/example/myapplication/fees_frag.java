package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fees_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fees_frag extends Fragment {

    List<String> listdegree;
    List<String> listfees;
    Map<String,List<String>> feeslist;
    ExpandableListView expandablelistview;
    ExpandableListAdapter expandableListAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fees_frag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fees_frag.
     */
    // TODO: Rename and change types and number of parameters
    public static fees_frag newInstance(String param1, String param2) {
        fees_frag fragment = new fees_frag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fees_frag, container, false);


        listdegree=new ArrayList<>();
        createGroupList();
        createCollection();
        //-----------------------------//
        expandablelistview=view.findViewById(R.id.expandableFees);
        expandableListAdapter= new degreeParentListAdapter(this.getContext(),listdegree,feeslist);
        expandablelistview.setAdapter(expandableListAdapter);
        expandablelistview.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int lastExpandedPosition=-1;
            @Override
            public void onGroupExpand(int i) {
                if(lastExpandedPosition!=-1 && i!=lastExpandedPosition)
                {
                    expandablelistview.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition=i;
            }
        });
        expandablelistview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                String selected =expandableListAdapter.getChild(i,i1).toString();
                Toast.makeText(getContext(),"Selected: " + selected,Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        return view;
    }


    private void createCollection() {
        String degree1 = "Per Credit Hour Fees : "+"8500";
        String degree2 = "Per Credit Hour Fees : "+"7500";
        String degree3 = "Per Credit Hour Fees : "+"6000";
        feeslist = new HashMap<String,List<String>>();
        for(String group:listdegree)
        {
            if(group.equals("BS-CS")){
                loadChild(degree1);
            }
            else if(group.equals("BS-SE")){
                loadChild(degree2);
            }
            else {
                loadChild(degree3);
            }
            feeslist.put(group,listfees);
        }
    }

    private void loadChild(String degree) {
        listfees = new ArrayList<>();
        listfees.add(degree);
    }

    private void createGroupList(){
        listdegree=new ArrayList<>();
        listdegree.add("BS-CS");
        listdegree.add("BS-SE");
        listdegree.add("BS-EE");

    }

}