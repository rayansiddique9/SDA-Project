package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FinancialAid_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FinancialAid_frag extends Fragment {

    aidInfo aidlist[] = {new aidInfo("PEEF Scolarship", "The Government of Punjab gives 42 scholarships to indigent students having domicile of Punjab. Newly admitted students in any campus of the university can apply for this scholarship.\n" +
            "The scholarship is for 4-year undergraduate studies and covers some portion of the tuition fee. The remaining tuition fee can be given by the University as Qarz-e-Hasna."),
            new aidInfo("OSAF Scolarship", "OSAF (Old Students Association of FAST) arranges financial assistance for those students who cannot afford to pay their full fee.")
        ,new aidInfo("Sindh Government Endowment Board Scholarships","The Sindh Government offers scholarships to students of Karachi campus on need-cum-merit for both under-graduate and graduate studies. The scholarship covers full tuition fee for entire duration of the program, renewable every year. The quota for students from rural sector is 60%, and the remaining 40% is for the students from urban sector. About 25 new scholarships are offered every year under this scheme.")
        ,new aidInfo("Study Loan","Realizing that the fees may not be affordable for some of its students, FAST arranges financial assistance in the form of interest-free study loans for bright indigent students. This assistance is subject to renewal every semester in light of the student’s academic performance. Financial assistance is limited to tuition fee only and is discontinued if the student’s CGPA falls below the minimum specified to avoid warning. Loan recipients MUST take full load of courses offered.")
    };

    ListView listView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FinancialAid_frag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FinancialAid_frag.
     */
    // TODO: Rename and change types and number of parameters
    public static FinancialAid_frag newInstance(String param1, String param2) {
        FinancialAid_frag fragment = new FinancialAid_frag();
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


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_financial_aid_frag, container, false);
        listView = view.findViewById(R.id.financialaidlist);
        adapterFinancialAid ad = new adapterFinancialAid(this.getContext(), R.layout.financial_adi_list_row, aidlist);
        listView.setAdapter(ad);

        return view;
    }
}