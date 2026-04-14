package com.vamshigollapelly.sportsfeedapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.vamshigollapelly.sportsfeedapp.R;
import com.vamshigollapelly.sportsfeedapp.adapters.NewsAdapter;
import com.vamshigollapelly.sportsfeedapp.data.BookmarkDatabase;
import com.vamshigollapelly.sportsfeedapp.data.NewsItem;
import com.vamshigollapelly.sportsfeedapp.data.NewsRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DetailFragment extends Fragment {

    private ExecutorService executor = Executors.newSingleThreadExecutor();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int newsId = getArguments() != null ? getArguments().getInt("newsId", 0) : 0;

        NewsItem item = null;
        for (NewsItem n : NewsRepository.getAllNews()) {
            if (n.id == newsId) { item = n; break; }
        }
        if (item == null) return;

        final NewsItem newsItem = item;

        ImageView imgDetail = (ImageView) view.findViewById(R.id.imgDetail);
        imgDetail.setImageResource(newsItem.imageRes);
        ((TextView) view.findViewById(R.id.tvDetailTitle)).setText(newsItem.title);
        ((TextView) view.findViewById(R.id.tvDetailDesc)).setText(newsItem.description);

        // Related stories - same category, exclude current
        List<NewsItem> related = new ArrayList<>();
        for (NewsItem n : NewsRepository.getAllNews()) {
            if (n.category.equals(newsItem.category) && n.id != newsItem.id) {
                related.add(n);
            }
        }
        RecyclerView rvRelated = view.findViewById(R.id.rvRelated);
        rvRelated.setLayoutManager(new LinearLayoutManager(getContext()));
        rvRelated.setAdapter(new NewsAdapter(related, relatedItem -> {}));

        // Bookmark button
        BookmarkDatabase db = BookmarkDatabase.getDatabase(requireContext());
        Button btnBookmark = view.findViewById(R.id.btnBookmark);

        executor.execute(() -> {
            boolean bookmarked = db.bookmarkDao().isBookmarked(newsItem.id);
            requireActivity().runOnUiThread(() ->
                    btnBookmark.setText(bookmarked ? "Remove Bookmark" : "Bookmark")
            );
        });

        btnBookmark.setOnClickListener(v -> {
            executor.execute(() -> {
                boolean bookmarked = db.bookmarkDao().isBookmarked(newsItem.id);
                if (bookmarked) {
                    db.bookmarkDao().delete(newsItem);
                    requireActivity().runOnUiThread(() -> {
                        btnBookmark.setText("Bookmark");
                        Toast.makeText(getContext(), "Removed from bookmarks", Toast.LENGTH_SHORT).show();
                    });
                } else {
                    db.bookmarkDao().insert(newsItem);
                    requireActivity().runOnUiThread(() -> {
                        btnBookmark.setText("Remove Bookmark");
                        Toast.makeText(getContext(), "Bookmarked!", Toast.LENGTH_SHORT).show();
                    });
                }
            });
        });
    }
}