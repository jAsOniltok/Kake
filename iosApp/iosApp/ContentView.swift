import SwiftUI
import shared

struct ContentView: View {
	let me = Testing().me

	var body: some View {
		Text(me)
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
