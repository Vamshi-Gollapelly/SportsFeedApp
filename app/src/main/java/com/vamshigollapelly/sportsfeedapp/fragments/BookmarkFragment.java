package com.vamshigollapelly.sportsfeedapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.vamshigollapelly.sportsfeedapp.R;
import com.vamshigollapelly.sportsfeedapp.adapters.NewsAdapter;
import com.vamshigollapelly.sportsfeedapp.data.BookmarkDatabase;
import com.vamshigollapelly.sportsfeedapp.data.NewsItem;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BookmarkFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bookmark, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rv = view.findViewById(R.id.rvBookmarks);
        rv.setLayoutManager(new GridLayoutManager(getContext(), 2));

        ExecutorService executor = Executors.newSingleThreadExecutor();
        BookmarkDatabase db = BookmarkDatabase.getDatabase(requireContext());

        executor.execute(() -> {
            List<NewsItem> bookmarks = db.bookmarkDao().getAll();
            requireActivity().runOnUiThread(() ->
                    rv.setAdapter(new NewsAdapter(bookmarks, item -> {}))
            );
        });
    }
}