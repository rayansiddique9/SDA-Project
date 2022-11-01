package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link programsOffered_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class programsOffered_frag extends Fragment {

    List<String> deptlist;
    List<String> listdegree;
    Map<String,List<String>> degreelist;
    ExpandableListView expandablelistview;
    ExpandableListAdapter expandableListAdapter;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public programsOffered_frag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment programsOffered_frag.
     */
    // TODO: Rename and change types and number of parameters
    public static programsOffered_frag newInstance(String param1, String param2) {
        programsOffered_frag fragment = new programsOffered_frag();
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
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_programs_offered_frag, container, false);


        deptlist=new ArrayList<>();
        createGroupList();
        createCollection();
        //-----------------------------//
        expandablelistview=view.findViewById(R.id.expandableDegrees);
        expandableListAdapter= new deptParentListAdapter(this.getContext(),deptlist,degreelist);
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
        String[] dept1 ={"BS-CS", "BS-DS", "BS-AI", "MS-CS"};
        String[] dept2 ={"BS-EE", "BS-CE", "MS-EE"};
        String[] dept3 ={"BS-BBA", "BS-AF", "MBA"};
        degreelist = new HashMap<String,List<String>>();
        for(String group:deptlist)
        {
            if(group.equals("Department Of \n Computing")){
                loadChild(dept1);
            }
            else if(group.equals("Department Of \n Engineering")){
                loadChild(dept2);
            }
            else {
                loadChild(dept3);
            }
            degreelist.put(group,listdegree);
        }
    }

    private void loadChild(String[] dept) {
        listdegree =new ArrayList<>();
        for(String Dept: dept)
        {
            listdegree.add(Dept);
        }

    }

    private void createGroupList(){
        deptlist=new ArrayList<>();
        deptlist.add("Department Of \n Computing");
        deptlist.add("Department Of \n Engineering");
        deptlist.add("Department Of \n Management Sciences");

    }


}