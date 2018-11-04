package martinpham.com.dictionaryz;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.support.v17.leanback.app.SearchFragment;
import android.os.Bundle;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ImageCardView;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.ObjectAdapter;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.support.v17.leanback.widget.SpeechRecognitionCallback;
import android.support.v4.app.ActivityOptionsCompat;
import android.util.Log;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class SearchActivityFragment extends SearchFragment implements SearchFragment.SearchResultProvider {

//    public SearchActivityFragment() {
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_search, container, false);
//    }

    private static final String TAG = "xxx";

    private static final int REQUEST_SPEECH = 0x00000010;
    private ArrayObjectAdapter mRowsAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());
        setSearchResultProvider(this);

        setOnItemViewClickedListener(new ItemViewClickedListener());


//        if (!Utils.hasPermission(getActivity(), Manifest.permission.RECORD_AUDIO)) {
//            // SpeechRecognitionCallback is not required and if not provided recognition will be handled
//            // using internal speech recognizer, in which case you must have RECORD_AUDIO permission
//            setSpeechRecognitionCallback(new SpeechRecognitionCallback() {
//                @Override
//                public void recognizeSpeech() {
//                    Log.v("xxx", "recognizeSpeech");
//                    try {
//                        startActivityForResult(getRecognizerIntent(), REQUEST_SPEECH);
//                    } catch (ActivityNotFoundException e) {
//                        Log.e("xxx", "Cannot find activity for speech recognizer", e);
//                    }
//                }
//            });
//        }
    }

    private final class ItemViewClickedListener implements OnItemViewClickedListener {
        @Override
        public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item,
                                  RowPresenter.ViewHolder rowViewHolder, Row row) {

            if (item instanceof Word) {
                Word word = (Word) item;
                Log.d(TAG, "Item: " + item.toString());
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra(DetailsActivity.ITEM, word);

                Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        getActivity(),
                        ((ImageCardView) itemViewHolder.view).getMainImageView(),
                        DetailsActivity.SHARED_ELEMENT_NAME)
                        .toBundle();
                getActivity().startActivity(intent, bundle);
            } else if (item instanceof String) {
                if (((String) item).contains(getString(R.string.error_fragment))) {
                    Intent intent = new Intent(getActivity(), BrowseErrorActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), ((String) item), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    @Override
    public ObjectAdapter getResultsAdapter() {
//        Log.d(TAG, "getResultsAdapter");
//        Log.d(TAG, mRowsAdapter.toString());

        // It should return search result here,

//        ArrayList<Word> words = WordRepository.findByName(keyword);
//        ArrayObjectAdapter listRowAdapter = new ArrayObjectAdapter(new CardPresenter());
//        listRowAdapter.addAll(0, words);
//        HeaderItem header = new HeaderItem("Search results");
//        mRowsAdapter.add(new ListRow(header, listRowAdapter));

        return mRowsAdapter;
    }

    @Override
    public boolean onQueryTextChange(String newQuery) {
//        Log.i(TAG, String.format("Search Query Text Change %s", newQuery));

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.i(TAG, String.format("Search Query Text Submit %s", query));

//        mRowsAdapter.removeItems(0, 1);

        WordRepository.findByName(getContext(), query, new Run(mRowsAdapter));
//        ArrayList<Word> words = WordRepository.findByName(query, getContext(), new Run());
//        ArrayObjectAdapter listRowAdapter = new ArrayObjectAdapter(new CardPresenter());
//        listRowAdapter.addAll(0, words);
//        HeaderItem header = new HeaderItem("Search results");
//        mRowsAdapter.add(new ListRow(header, listRowAdapter));



        return true;
    }

    class Run implements WordRepository.Runnable {
        private ArrayObjectAdapter mRowsAdapter;

        public Run(ArrayObjectAdapter mRowsAdapter) {
            this.mRowsAdapter = mRowsAdapter;
        }

        @Override
        public void run(String result) {

        }

        @Override
        public void run(ArrayList<Word> words)
        {
            mRowsAdapter.removeItems(0, 1);
            ArrayObjectAdapter listRowAdapter = new ArrayObjectAdapter(new CardPresenter());
            listRowAdapter.addAll(0, words);
            HeaderItem header = new HeaderItem("Search results");
            mRowsAdapter.add(new ListRow(header, listRowAdapter));
        }
    }
}
