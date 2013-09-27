package graphics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentSkipListSet;

public class SubsetOrderEngine {
	
	public SubsetOrderEngine() {
		
	}
	
	/**
	 * Tutaj tak naprawde bedzie zwykle sortowanie tylko wg TextureID 
	 * @param list
	 * @return
	 */
	public List<ModelSubset> orderSubsetsInList(List<GameObject> list) {
		
		Comparator<ModelSubset> comp = new Comparator<ModelSubset>() {

			@Override
			public int compare(ModelSubset o1, ModelSubset o2) {
				if (o1.getMaterial().getTextureID()<o2.getMaterial().getTextureID())
					return -1;
				if (o1.getMaterial().getTextureID()>o2.getMaterial().getTextureID())
					return 1;
				return 0;
			}
		};
		
		List<ModelSubset> sets = new ArrayList<ModelSubset>();
		
		for (GameObject obj : list) {
			ModelSubset[] subarr = obj.getAnimatedModel().getSubsets();
			for (int i = 0; i<subarr.length; i++) {
				sets.add(subarr[i]);
			}
		}
		
		Collections.sort(sets, comp);
		
		return sets;
	}

}
