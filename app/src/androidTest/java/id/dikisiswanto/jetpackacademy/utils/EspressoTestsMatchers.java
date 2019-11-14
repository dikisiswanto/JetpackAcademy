package id.dikisiswanto.jetpackacademy.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class EspressoTestsMatchers {
	public static Matcher<View> withDrawable(final int resourceId) {
		return new DrawableMatcher(resourceId);
	}

	public static Matcher<View> noDrawable() {
		return new DrawableMatcher(-1);
	}

	public static class DrawableMatcher extends TypeSafeMatcher<View> {
		private final int expectedId;
		private String resourceName;

		public DrawableMatcher(int resourceId) {
			super(View.class);
			this.expectedId = resourceId;
		}

		@Override
		protected boolean matchesSafely(View item) {
			if (!(item instanceof ImageView)) {
				return false;
			}
			ImageView imageView = (ImageView) item;
			if (expectedId < 0) {
				return imageView.getDrawable() == null;
			}
			Resources resources = item.getContext().getResources();
			Drawable expectedDrawable = resources.getDrawable(expectedId);
			resourceName = resources.getResourceEntryName(expectedId);
			if (expectedDrawable == null) {
				return false;
			}
			Bitmap bitmap = getBitmap(imageView.getDrawable());
			Bitmap otherBitmap = getBitmap(expectedDrawable);
			return bitmap.sameAs(otherBitmap);
		}

		private Bitmap getBitmap(Drawable drawable) {
			Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
					drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
			Canvas canvas = new Canvas(bitmap);
			drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
			drawable.draw(canvas);
			return bitmap;
		}


		@Override
		public void describeTo(Description description) {
			description.appendText("with drawable from resource id: ");
			description.appendValue(expectedId);
		}
	}
}

